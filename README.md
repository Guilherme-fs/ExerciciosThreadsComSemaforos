# ExerciciosThreadsComSemaforos

Este repositório contém exercícios de programação concorrente em Java, utilizando **Threads** e **Semáforos** para controle de concorrência.

## Exercício 1 – Ping com Threads
- Cada servidor (`Google`, `UOL`, `Terra`) é acessado por uma thread que executa um comando `ping`.
- O programa identifica o sistema operacional e, se for Linux, executa o `ping` e extrai o tempo médio de resposta.
- Uso da função `process.waitFor()` para aguardar o término do comando antes de prosseguir.
- Objetivo: praticar o uso de threads e captura da saída de processos externos no Java.

---

## Exercício 2 – Processos com Transações de Banco de Dados
- Simulação de processos que realizam **cálculos** e **transações de banco de dados**.
- Cada thread, de acordo com seu `id % 3`, executa um número específico de cálculos e acessos ao banco.
- O semáforo controla o acesso exclusivo às transações de banco de dados, garantindo que apenas uma thread por vez execute essa parte crítica.
- Objetivo: aplicar semáforos em situações de exclusão mútua.

---

## Exercício 3 – Prova com Corrida, Tiro ao Alvo e Ciclismo
- Simulação de uma prova com três etapas:
  1. Corrida (300 metros)
  2. Tiro ao alvo (3 disparos, com uso limitado de armas controlado por semáforo)
  3. Ciclismo (500 metros)
- Cada competidor é uma thread que percorre as etapas e acumula pontuação:
  - Pontos de ordem de chegada (250 para o primeiro, decrescendo a cada competidor).
  - Pontos de precisão no tiro ao alvo.
- Ao final, os resultados são armazenados em uma lista, ordenados e exibidos em formato de ranking.
- Objetivo: aplicar semáforos em cenários com **recursos limitados** (armas) e organizar ranking com base em pontuação.

---

## Tecnologias utilizadas
- Java
- Threads (`extends Thread`)
- Semáforos (`java.util.concurrent.Semaphore`)
- Sincronização de listas (`Collections.synchronizedList`)
- Ordenação com `Comparator`