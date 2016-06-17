# README #

Este trabalho consiste na implementação de um programa (ou um par de programas) que cifre/decifre
arquivos utilizando o algoritmo de criptografia de chave pública RSA. Seja na forma
de um único programa parametrizável ou de um par de programas, a implementação deve:
• cifrar um arquivo qualquer especificado pelo usuário;
• decifrar um arquivo previamente cifrado especificado pelo usuário.
Em ambos os casos de uso, o usuário especificará a chave a ser utilizada na forma de um
par de números inteiros (n e d ou n e e). A implementação pode assumir que 255 < n < 65536.
O programa deve cifrar o arquivo de entrada utilizando blocos de 1 byte. Em outras palavras,
cada byte será cifrado individualmente, independente dos demais. Como n < 65536 = 216
,
o texto cifrado correspondente terá, no máximo, 2 bytes. Logo, cada byte original cifrado deve
ser representado como um número inteiro de 16 bits no arquivo de saída. Note que, mesmo
que o texto cifrado seja menor ou igual a 255, o mesmo deve ser escrito no arquivo de
saída com 2 bytes.
Por outro lado, a parte da implementação responsável por decifrar um arquivo cifrado deve
ler o arquivo de entrada especificado pelo usuário em blocos de 2 bytes. Cada bloco deve ser
decifrado usando a chave provida pelo usuário. O programa deve assumir que o texto plano
correspondente sempre terá comprimento máximo de 1 byte. Se, ao decifrar um bloco, o
programa encontrar um texto plano maior que 255, a execução deve ser abortada, exibindose
uma mensagem apropriada ao usuário, explicando que o arquivo de entrada era inválido.
Arquivos também devem ser considerados inválidos por este módulo se possuírem um número
ímpar de bytes.
Cuidados especiais devem ser tomados para evitar a ocorrência de overflow durante as
exponenciações realizadas pelo código. Um método rápido para execução destas exponenciações
modulares sem risco de overflow é a chamada exponenciação binária. Na sua forma
mais simples, este método pode ser implementado conforme mostrado no Algoritmo 1.
Embora o código mostrado seja escrito em C, implementações equivalentes podem ser
realizadas em qualquer linguagem. O código se baseia na seguinte propriedade da exponenciação:
1 /*
2 * Argumentos:
3 * - b: base da exponenciação.
4 * - e: expoente.
5 * - n: módulo (n > 1).
6 * Retorna: b^e (mod n).
7 */
8 unsigned short binExp(unsigned short b, int e, unsigned short n) {
9
10 unsigned int res = b;
11 unsigned int y = 1;
12
13 /* Caso base. */
14 if (e == 0) return(1);
15
16 while(e > 1) {
17
18 if (e & 1) {
19
20 /*
21 * Caso especial: expoente é ímpar.
22 * Acumular uma potência de ’res’ em ’y’.
23 */
24 y = (y * res) % (unsigned int) n;
25 e = e - 1;
26 }
27
28 /*
29 * Elevamos ’res’ ao quadrado, dividimos expoente por 2.
30 */
31 res = (res * res) % (unsigned int) n;
32 e = e / 2;
33 }
34
35 return((unsigned short) ((res * y) % n));
36 }

A ideia do algoritmo é progressivamente reduzir o expoente por um fator de 2, enquanto a
base é aumentada de acordo. A cada iteração, obtemos um novo valor para a base elevando
seu valor atual ao quadrado. Como consequência, o expoente atual pode ser dividido por 2.
Um caso especial ocorre quando, em uma dada iteração, o expoente é ímpar: neste caso, para
evitar expoentes fracionários, a base é colocada em evidência e acumulada (através de uma
multiplicação) em uma variável auxiliar. Ao final do processamento (i.e., quando o expoente
chega a 1), a base atual é multiplicada pela variável auxiliar para se chegar ao resultado final.
Considere, por exemplo, o problema de calcular a potência 2
11. Na primeira iteração, como
11 é ímpar, coloca-se a base (2, neste ponto) em evidência, multiplicando seu valor pelo valor
atual da variável auxiliar (inicializada com 1). Agora, como o expoente passa a ser par, podese
dividi-lo por 2, elevando a base ao quadrado. Obtém-se, então, 2 ·

2
2
5 = 2 · 4
5
. Em uma
nova iteração, nota-se que, novamente, o expoente é ímpar. Repete-se o processo, colocando
a base (agora igual a 4) em evidência e alterando base e expoente, obtendo-se 2 · 4 ·

4
2
2
= 8 · 162
. Na próxima iteração, como o expoente já é par, basta manipular expoente e base,
obtendo-se 8·2561
. Com o expoente igual a 1, as iterações terminam e basta multiplicar a base
pela variável auxiliar obtendo o resultado final (2048, neste exemplo).
Como no caso de interesse deste trabalho a exponenciação é realizada em módulo n, a cada
atualização da base ou da variável auxiliar podemos calcular o módulo, de forma a manter os
valores de ambas as variáveis relativamente baixos. Particularmente, note que a base (valor a
ser cifrado/decifrado) é, necessariamente menor que n < 2
16 e isso se mantém verdade a cada
nova iteração por conta do módulo. Logo, b
2 <

2
162 = 232. Isto significa que a multiplicação
realizada na linha 31 nunca resultará em um número com mais de 32 bits (tamanho do tipo int
em C), evitando a possibilidade de overflow na atualização da base. O mesmo raciocínio pode
ser usado para mostrar que nunca haverá overflow na atualização da variável auxiliar y.
O trabalho poderá ser feito em grupos de até 4 alunos. A linguagem de programação é de
livre escolha de cada grupo, assim como detalhes de interface com o usuário (e.g., interface
gráfica, interface de linha de comando, obtenção dos nomes dos arquivos de entrada e saída,
obtenção da chave). No entanto, todos os passos do RSA deverão ser implementados pelo
grupo, incluindo os passos de exponenciação modular. Não serão aceitos trabalhos que
utilizem bibliotecas/funções de terceiros para estas funcionalidades.
Para facilitar a execução de testes para a verificação da corretude da implementação, podese
utilizar o seguinte par de chaves pública e privada: (2881, 1625) e (2881, 29).
Requisitos
A implementação deverá cumprir os seguintes requisitos:
1. possuir um módulo para cifrar e um módulo decifrar, seja em um único programa executável
ou em dois programas separados;
2. obter os nomes dos arquivos de entrada e saída a partir do usuário (a maneira pela qual
isso é feito não é importante);
3. obter o valor da chave a ser usada para cifrar/decifrar o arquivo de entrada (a maneira
pela qual isso é feito não é importante);
4. o módulo para cifrar deverá realizar a cifra em blocos de 1 byte e gerar como saída um arquivo
no qual cada bloco de 16 bits consecutivos corresponde ao byte do arquivo original
corretamente cifrado com o RSA utilizando a chave especificada;
5. o módulo para decifrar deverá operar sobre blocos de 2 bytes, gerando como saída bytes
individuais correspondentes ao texto plano decifrado pelo RSA utilizando a chave especificada;
6. o módulo para decifrar deverá verificar se o texto plano associado a um bloco de entrada
é maior que 255; neste caso, a execução deverá ser abortada, informando o erro ao
usuário;
7. o módulo para decifrar deverá verificar o arquivo de entrada possui um número ímpar de
bytes; neste caso, a execução deverá ser abortada, informando o erro ao usuário;
8. ambos os módulos devem gerar os mesmos resultados independentemente do endianess
do computador no qual são executados.