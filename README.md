Projeto Android de exemplo para a apresentação no Android Meetup de Belo Horizonte sobre Como Isolar o Código de Teste no Android.

Estruturação do Projeto:
  - Camada de UI => Feature by package
  - Arquitetura MVP
  - Pattern Repository, utilizando MVP para a arquitetura e as bibliotecas RequestMatcher e Mockito para testes.
  - Mockito para teste de comportamento da camada de orquestração - Presenter (http://site.mockito.org/)
  - RequestMatcher para teste de UI isolando a chamada à API (https://github.com/concretesolutions/requestmatcher)
  - Espresso Intents para fazer stub de Intents (https://developer.android.com/training/testing/espresso/intents.html)
  - Android Test Orchestrator para fazer a orquestração da exeução dos testes instrumentados (https://developer.android.com/training/testing/junit-runner.html#using-android-test-orchestrator)
