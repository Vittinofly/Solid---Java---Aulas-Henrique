# Os Princípios SOLID com Java e JUnit 5

**Centro Universitário Unibras – Montes Belos**
Engenharia de Software – 6º Período – 2026/2
Disciplina: Arquitetura e Projeto de Software – Prof. Henrique Santiago
Aluno(a): **[SEU NOME AQUI]**

---

## Sumário

1. [Introdução](#1-introdução)
2. [Tecnologias e como executar](#2-tecnologias-e-como-executar)
3. [Estrutura do projeto](#3-estrutura-do-projeto)
4. [S – Single Responsibility Principle](#4-s--single-responsibility-principle)
5. [O – Open/Closed Principle](#5-o--openclosed-principle)
6. [L – Liskov Substitution Principle](#6-l--liskov-substitution-principle)
7. [I – Interface Segregation Principle](#7-i--interface-segregation-principle)
8. [D – Dependency Inversion Principle](#8-d--dependency-inversion-principle)
9. [Conclusão](#9-conclusão)
10. [Referências](#10-referências)

---

## 1. Introdução

SOLID é um acrônimo, popularizado por Robert C. Martin ("Uncle Bob") no início dos anos 2000 e
nomeado por Michael Feathers, que reúne cinco princípios de projeto orientado a objetos. O objetivo
comum a todos eles é **controlar o acoplamento e aumentar a coesão**, produzindo código mais fácil de
entender, testar, estender e manter. Segundo Aniche (2016), classes pouco coesas e muito acopladas são
a principal causa de código difícil de evoluir; os princípios SOLID são heurísticas para evitar esse
cenário.

Neste projeto, cada princípio é apresentado com:

- um **exemplo que viola** o princípio (pacote `violacao`);
- a **versão refatorada** que o respeita (pacote `refatorado`);
- **testes unitários com JUnit 5** que validam a versão refatorada e evidenciam o problema da violação.

## 2. Tecnologias e como executar

- Java 17 (ou superior)
- Apache Maven 3.9+
- JUnit 5 (Jupiter) 5.11

```bash
# compilar e executar todos os testes
mvn test
```

Resultado esperado: **20 testes executados, 0 falhas**.

## 3. Estrutura do projeto

```
solid-java/
├── pom.xml
├── README.md
├── .gitignore
└── src
    ├── main/java/br/edu/unibras/solid
    │   ├── srp/ { violacao, refatorado }
    │   ├── ocp/ { violacao, refatorado }
    │   ├── lsp/ { violacao, refatorado }
    │   ├── isp/ { violacao, refatorado }
    │   └── dip/ { violacao, refatorado }
    └── test/java/br/edu/unibras/solid
        ├── srp/CalculadoraSalarioTest.java
        ├── ocp/DescontoTest.java
        ├── lsp/LiskovTest.java
        ├── isp/RoboTest.java
        └── dip/PedidoServiceTest.java
```

---

## 4. S – Single Responsibility Principle

> "Uma classe deve ter um, e apenas um, motivo para mudar." — Robert C. Martin

**Fundamentação.** Responsabilidade, aqui, significa *motivo para mudar*. Quando uma classe concentra
regra de negócio, persistência e apresentação, qualquer mudança em uma dessas áreas obriga a alterar
(e a testar novamente) a classe inteira. Em *Clean Architecture* (2017), Martin reformula o princípio:
um módulo deve responder a **um único ator** (por exemplo, o RH que define a regra salarial e o DBA que
define a persistência são atores diferentes). Separar as responsabilidades aumenta a coesão e permite
testar a regra de negócio sem banco de dados.

**Violação** — [`srp/violacao/Funcionario.java`](src/main/java/br/edu/unibras/solid/srp/violacao/Funcionario.java):
a mesma classe guarda os dados, calcula o salário líquido e grava no "banco".

**Refatoração** — [`srp/refatorado`](src/main/java/br/edu/unibras/solid/srp/refatorado):

| Classe | Única responsabilidade |
|---|---|
| `Funcionario` | Representar os dados do funcionário |
| `CalculadoraSalario` | Regra de negócio (salário líquido = bruto × 0,8) |
| `FuncionarioRepository` | Persistência |

**Teste** — `CalculadoraSalarioTest`: a regra salarial é testada isoladamente
(`5000 → 4000`), sem nenhuma dependência de persistência.

## 5. O – Open/Closed Principle

> "Entidades de software devem ser abertas para extensão, mas fechadas para modificação." — Bertrand Meyer (1988)

**Fundamentação.** Proposto por Meyer e reinterpretado por Martin com foco em polimorfismo, o OCP diz
que deve ser possível acrescentar comportamento **criando código novo**, e não editando código que já
funciona e já foi testado. A técnica mais comum é depender de uma abstração (interface) e criar novas
implementações. O sintoma clássico de violação é uma cadeia de `if/else` ou `switch` sobre um "tipo".

**Violação** — [`ocp/violacao/CalculadoraDesconto.java`](src/main/java/br/edu/unibras/solid/ocp/violacao/CalculadoraDesconto.java):
cada novo tipo de cliente exige um novo `if` dentro da mesma classe.

**Refatoração** — interface `DescontoService` com as implementações `DescontoVip` (15%),
`DescontoEstudante` (10%) e `SemDesconto`. A `CalculadoraPreco` recebe qualquer `DescontoService` e
nunca precisa ser alterada.

**Teste** — `DescontoTest`: além do teste do enunciado (`100 → 85`), um teste cria um **desconto novo
via lambda** e o usa na `CalculadoraPreco` sem modificar nenhuma classe existente.

## 6. L – Liskov Substitution Principle

> "Se S é um subtipo de T, então objetos do tipo T podem ser substituídos por objetos do tipo S sem
> alterar as propriedades desejáveis do programa." — Barbara Liskov (1987)

**Fundamentação.** Herança não é apenas reaproveitamento de código: é um **contrato**. O subtipo não pode
exigir mais (pré-condições mais fortes) nem entregar menos (pós-condições mais fracas) que o tipo base,
nem lançar exceções que o cliente não espera. Sinais de violação: métodos sobrescritos que lançam
`UnsupportedOperationException`, ou código cliente cheio de `instanceof`.

**Violação** — [`lsp/violacao`](src/main/java/br/edu/unibras/solid/lsp/violacao): `ContaInvestimento`
herda de `ContaBancaria`, mas `sacar()` lança exceção. Um método que recebe "qualquer conta" e faz um
saque quebra quando recebe uma `ContaInvestimento`.

**Refatoração** — [`lsp/refatorado`](src/main/java/br/edu/unibras/solid/lsp/refatorado): a interface
`ContaBancaria` passa a conter apenas o que **toda** conta cumpre (`depositar`, `getSaldo`). A capacidade
de saque fica em `ContaSacavel`, implementada só por `ContaCorrente`. `ContaInvestimento` (depósito com
rendimento de 2%) implementa apenas `ContaBancaria`, cumprindo integralmente seu contrato.

**Teste** — `LiskovTest`: o mesmo código cliente percorre uma lista de `ContaBancaria` com tipos
diferentes sem falhas; outro teste mostra a exceção lançada na versão com violação.

## 7. I – Interface Segregation Principle

> "Clientes não devem ser forçados a depender de métodos que não utilizam." — Robert C. Martin

**Fundamentação.** Interfaces "gordas" acoplam classes a operações irrelevantes para elas: qualquer
alteração na interface afeta todas as implementações, e surgem métodos vazios ou que lançam exceção.
A solução é dividir a interface em **interfaces menores e coesas**, focadas no papel que cada cliente
precisa, e deixar cada classe implementar apenas as que fazem sentido.

**Violação** — [`isp/violacao`](src/main/java/br/edu/unibras/solid/isp/violacao): a interface
`Trabalhador` exige `trabalhar()` e `comer()`; o `Robo` é obrigado a implementar `comer()` lançando exceção.

**Refatoração** — interfaces `Trabalhador` e `Alimentavel` separadas. `Robo` implementa só
`Trabalhador`; `Humano` implementa as duas.

**Teste** — `RoboTest`: o robô trabalha sem erros e **não** depende de `Alimentavel`; a versão com
violação lança exceção ao chamar `comer()`.

## 8. D – Dependency Inversion Principle

> "Módulos de alto nível não devem depender de módulos de baixo nível. Ambos devem depender de
> abstrações. Abstrações não devem depender de detalhes; detalhes devem depender de abstrações."
> — Robert C. Martin

**Fundamentação.** A regra de negócio (alto nível) é a parte mais valiosa e estável do sistema; ela não
deve ficar presa a detalhes voláteis como e-mail, SMS ou um banco específico. Ao depender de uma
interface e receber a implementação de fora (**injeção de dependência**, normalmente pelo construtor), a
classe fica desacoplada, a implementação pode ser trocada sem alteração e os testes podem usar dublês
(mocks/fakes). O DIP é a base de frameworks como Spring e da própria *Clean Architecture*.

**Violação** — [`dip/violacao/PedidoService.java`](src/main/java/br/edu/unibras/solid/dip/violacao/PedidoService.java):
instancia `new EmailNotificador()` internamente; não é possível trocar o canal nem isolar o teste.

**Refatoração** — interface `Notificador` com `EmailNotificador` e `SmsNotificador`. O `PedidoService`
recebe um `Notificador` no construtor.

**Teste** — `PedidoServiceTest`: um **mock escrito com lambda** verifica a mensagem
"Pedido Confirmado!", e outro teste usa uma lista como dublê para garantir que exatamente uma
notificação foi enviada.

---

## 9. Conclusão

Os cinco princípios convergem para o mesmo objetivo: **alta coesão e baixo acoplamento**. Em todos os
exemplos, a versão refatorada ficou mais fácil de testar — o que confirma a observação de Aniche (2016)
de que a dificuldade de escrever um teste é um forte indicativo de problema de projeto. SRP e ISP
favorecem classes e interfaces pequenas e focadas; OCP e DIP usam abstrações para permitir extensão
sem modificação; e LSP garante que essas abstrações sejam usadas com segurança pelo polimorfismo.

## 10. Referências

- SILVA, Maurício Aniche. **Desbravando o SOLID**: princípios da programação orientada a objetos. São Paulo: Casa do Código, 2016. Disponível em: https://www.casadocodigo.com.br/products/livro-desbravando-solid.
- SOARES, Rodrigo. **Orientação a Objetos e SOLID para Microsserviços**. São Paulo: Casa do Código. Disponível em: https://www.casadocodigo.com.br/products/livro-oo-solid.
- CASA DO CÓDIGO. **TDD**: Test-Driven Development na prática. São Paulo: Casa do Código. Disponível em: https://www.casadocodigo.com.br/products/livro-tdd.
- MARTIN, Robert C. **Clean Code**: A Handbook of Agile Software Craftsmanship. Boston: Prentice Hall, 2008.
- MARTIN, Robert C. **Clean Architecture**: A Craftsman's Guide to Software Structure and Design. Boston: Prentice Hall, 2017.
- LISKOV, Barbara. Data Abstraction and Hierarchy. **SIGPLAN Notices**, v. 23, n. 5, 1988 (OOPSLA '87).
- MEYER, Bertrand. **Object-Oriented Software Construction**. Prentice Hall, 1988.
- DESCHAMPS, Filipe. **SOLID is EASY with These Illustrations**. YouTube, 2020. Disponível em: https://www.youtube.com/watch?v=6SfrO3D4dHM.
- YOUTUBE. **Vídeo Complementar sobre SOLID (I)**. Disponível em: https://www.youtube.com/watch?v=_jDNAf3CzeY.
- YOUTUBE. **Vídeo Complementar sobre SOLID (II)**. Disponível em: https://www.youtube.com/watch?v=f6-5ANuTkys.
- YOUTUBE. **Vídeo Complementar sobre SOLID (III)**. Disponível em: https://www.youtube.com/watch?v=T6pF7BfAPIo.
