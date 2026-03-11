<h2>Exercise 1</h2>
<p>What value of P did you choose, and why?</p>
R: Escolhi P = 5L pois se dormir durante muito tempo temos muitas alterações que não foram vistas.

<p>Why can polling miss short-lived states?</p>
R: Porque se o estado for alterado e depois de um tempo inferior a P voltar ao estado anterior, o polling pode não detetar essa mudança.

<h2>Exercise 2</h2>
<p>Which states did you observe, and in what order?</p>
R: Em teoria, a ordem completa e correta que a thread atravessa neste código é:
NEW ➔ RUNNABLE ➔ TIMED_WAITING ➔ RUNNABLE ➔ TERMINATED
NEW: Antes de chamares o start(). 
RUNNABLE: Assim que chamas o start(), a thread fica pronta a executar (ou já a executar os prints iniciais).
TIMED_WAITING: Quando a thread atinge a linha Thread.sleep(2000), o sistema operativo retira-a do processador 
e coloca-a em espera durante 2 segundos.
RUNNABLE: Passados os 2 segundos, a thread acorda e volta a estar pronta a executar o resto do código (o print "end").
TERMINATED: Quando o bloco de código termina.

<p>Did you always observe the RUNNABLE state? If not, why might that happen?</p>
R: TODO.

<h2>Exercise 3</h2>
<p>Consider two different outputs (short excerpts are enough). Why do they differ?</p>
R:Eles diferem porque a execução de múltiplas threads é não-determinística.
O teu programa não tem controlo sobre quando é que uma thread corre no processador. Quem decide isso é o escalonador
(scheduler) do Sistema Operativo.
O escalonador pode decidir parar a Thread A a meio do ciclo para dar um bocado de processador à Thread B, e na execução
seguinte pode fazer as pausas em momentos completamente diferentes. Como não controlas o escalonador, a ordem dos 
muda sempre.

<p>What does interleaving mean in this context?</p>
R:Interleaving é a mistura ou alternância da execução das instruções de diferentes threads no tempo.
Como tens duas threads a tentar escrever para o mesmo ecrã (a consola), os prints da Thread A e da Thread B 
vão aparecer "baralhados" (ex: A1, A2, B1, A3, B2, B3...). Esta mistura visível no ecrã é a prova de que o 
processador está a saltar rapidamente (a entrelaçar a execução) entre o código da thread A e o código da thread B.

<h2>Exercise 4</h2>
1. O entrelaçamento (interleaving) observado muda entre as duas variantes? De que forma?
R: Sim, muda significativamente. Na versão pesada (com prints em todas as iterações), notas muito mais saltos e 
alternâncias entre a Thread A e a Thread B no ecrã. Na versão leve, como os prints são muito mais raros 
(de 100 em 100), o processamento do ecrã parece muito mais "arrumado" e limpo, dando a falsa ilusão de que as threads 
correm de forma mais sequencial ou arrumada do que na realidade estão a correr.

2. Porque é que o output para a consola pode influenciar o escalonamento (scheduling) e o tempo?
R: Porque escrever para a consola (println) é uma operação de entrada/saída (I/O). Operações de I/O são lentas 
comparadas com a velocidade do processador. Quando uma thread pede para escrever no ecrã, o sistema operativo muitas 
vezes adormece temporariamente essa thread enquanto o ecrã/consola processa a mensagem, dando oportunidade à outra 
thread de correr. Ou seja, a própria instrumentação (o código que metemos para ver o que se passa, como os prints) 
altera o comportamento daquilo que estamos a tentar observar (é o chamado Efeito do Observador).