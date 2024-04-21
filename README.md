# Quiz_API
API de questionário com Spring - Trabalho da disciplina de Desenvolvimento Web

## (POST) /questionarios

```
{
  "description": "João",
  "idQuestion": {
    "description": "Qual é o seu nome?",
    "idQuiz": {
      "name": "ENEM",
      "description": "Português",
      "idUserCreate": null
    }
  }
}
```

## (POST) /quiz

```
{
    "name": "ENEM",
    "description": "Português",
    "idUserCreate": null
}
```

## (PUT/PATCH) /questionarios/{id}

```
{
    "name": "MEC",
    "description": "Matemática",
    "idUserCreate": null
}
```

## (POST) questionario/{id}/resposta

```
{
  "description": "5",
  "idQuestion": {
    "description": "2+3?"
  }
}
```

## (DELETE) 

```
    1. /resposta/1
    2. /pergunta/1
    3. /questionarios/1
```