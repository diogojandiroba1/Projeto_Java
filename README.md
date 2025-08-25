# ⚽ Sistema de Gerenciamento de Time de Futebol

Este projeto é um sistema simples em **Java** para gerenciar um time de futebol.  
Ele permite cadastrar jogadores, preparar treinos e organizar a equipe.

---

## 📌 Funcionalidades

- Cadastro de **jogadores** com:
  - Nome, idade e salário (herdado da classe `Pessoa`)
  - Posição em campo
  - Altura e peso
  - Estado de lesão
  - Cálculo automático do IMC

- Cadastro de **preparador físico**
    -Avaliar condição física de jogadores (cansaço, lesão).
    Recomendação de treinos específicos (força, coletivo, recuperação).

- Organização de **treinos**:
  - Agendamento de treinos no calendário.
  - Treino individual
  - Treino coletivo

- Gerenciamento de **time**:
  - Adicionar jogadores
  - Remover jogadores
  - Listar elenco
  - Associar preparador físico
  - Buscar jogador por posição
 
- Persistência de dados
---

## 📂 Estrutura das Classes

- `Pessoa`  
  Classe base com informações comuns como nome, idade e salário.

- `Jogador`  
  Herda de `Pessoa` e adiciona atributos específicos de jogadores.

- `PreparadorFisico`  
  Herda de `Pessoa` e representa o preparador responsável pelos treinos.

- `Treino`  
  Classe base para diferentes tipos de treinos.

- `TreinoColetivo`  
  Herda de `Treino`. Especialização de treino realizado em grupo.

- `Time`  
  Gerencia jogadores e comissão tecnica de uma equipe.

---

## 🚀 Tecnologias Utilizadas

- **Java**
- **Paradigma Orientado a Objetos (POO)**

