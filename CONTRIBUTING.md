# Como contribuir com o JogaAí

Leia tudo antes de começar. Qualquer dúvida, fale com o Gustavo **antes** de sair mudando.

---

## 1. Quem faz o quê

| Pessoa | Tela(s) | Pasta |
|---|---|---|
| **Gustavo** | Detalhe de Jogos + Painel | `ui/jogo/`, `ui/painel/` |
| **Luis** | Empréstimos + Reservas | `ui/emprestimo/`, `ui/reserva/` |
| **Enzo** | Exemplares | `ui/exemplar/` |
| **Cauã** | Pessoas | `ui/pessoa/` |
| **Mateus** | Categorias | `ui/categoria/` |

Cada tela já existe como arquivo vazio e já está ligada na navegação. Você só precisa trocar o conteúdo.

**Ordem de trabalho:** faça primeiro a **tela de lista**. Detalhe e formulário (criar/editar) só depois que o detalhe de Jogos estiver na `main`, para todo mundo seguir o mesmo padrão.

---

## 2. Onde você pode mexer

✅ **Pode mexer à vontade:** a sua pasta em `ui/`.

⚠️ **Pode adicionar, sem mudar o que existe:** o fake da sua entidade em `data/repository/`. Pode incluir novos exemplos, mas **não altere nem apague os ids existentes**: os outros fakes dependem deles (um empréstimo aponta para um usuário, um exemplar aponta para um jogo).

🚫 **Não mexa sem falar com o Gustavo:**
- `domain/model/` (os modelos)
- `data/repository/*Repository.kt` (as interfaces)
- `data/AppModule.kt`
- `ui/navigation/`
- `ui/shared/` e `theme/`

Esses arquivos são usados por todo mundo. Uma mudança sua quebra a tela de outra pessoa.

---

## 3. Como montar uma tela

**Copie a estrutura de `ui/jogo/`.** Toda tela tem três arquivos:

| Arquivo | O que tem |
|---|---|
| `XScreenUiState.kt` | `data class` com todo o estado da tela, só com `val` |
| `XViewModel.kt` | carrega os dados e muda o estado |
| `XScreen.kt` | desenha o estado na tela |

### O ViewModel

Todo ViewModel **herda de `BaseViewModel`** (em `ui/shared/`). Ela já cuida do estado, do loading e dos erros. Você só ensina como ligar o loading e como colocar o erro no seu estado:

```kotlin
class ExemplarViewModel(
    private val repository: ExemplarRepository,
) : BaseViewModel<ExemplarScreenUiState>(ExemplarScreenUiState()) {

    override fun comCarregando(estado: ExemplarScreenUiState, carregando: Boolean) =
        estado.copy(isLoading = carregando)

    override fun comErro(estado: ExemplarScreenUiState, mensagem: String) =
        estado.copy(errorMessage = mensagem)

    fun carregar() {
        runAction("Erro ao carregar exemplares") {
            val lista = repository.listar()
            _uiState.value = _uiState.value.copy(exemplares = lista)
        }
    }

    init {
        carregar()
    }
}
```

- O seu `UiState` precisa ter `isLoading: Boolean = false` e `errorMessage: String = ""`.
- As duas funções `override` o IntelliJ gera sozinho: cursor no nome da classe → `Alt+Enter` → **Implement members**.
- Use `runAction` **só quando chamar o repositório**. O texto que você passa é a mensagem mostrada se der um erro inesperado, então seja específico ("Erro ao carregar exemplares", não "Erro").
- Para mudar estado local (busca, filtro, item selecionado), **não** use `runAction`: faça `_uiState.value = _uiState.value.copy(...)` direto.
- Toda chamada que muda o estado precisa começar com `_uiState.value =`. `copy` devolve um estado novo; se você não atribuir, a mudança se perde.
- Pegue os repositórios do `AppModule` (ex.: `AppModule.emprestimoRepository`).

### A tela cria o próprio ViewModel

Para ninguém precisar editar a navegação, o ViewModel entra como **parâmetro com valor padrão**:

```kotlin
@Composable
fun ExemplarScreen(
    viewModel: ExemplarViewModel = remember {
        ExemplarViewModel(AppModule.exemplarRepository, AppModule.jogoRepository)
    }
) {
    val uiState by viewModel.uiState.collectAsState()
    // ...
}
```

### Os quatro estados

Toda tela de lista trata, **nesta ordem**, dentro de um `when`:

1. **Carregando** → `CircularProgressIndicator` centralizado (copie do `JogoScreen`)
2. **Erro** → `EstadoDaTela` com a mensagem e o botão "Tentar novamente"
3. **Vazio** → `EstadoDaTela`, e se houver filtro ativo, com o botão "Limpar filtros"
4. **Conteúdo** → a lista

A ordem importa: o `when` para no primeiro ramo verdadeiro. Se o vazio vier antes do erro, o erro nunca aparece.

---

## 4. Regras de código

**Imports**
1. No `commonMain`, os imports só podem começar com `androidx.`, `kotlin.`, `kotlinx.` ou `com.jogaai.`.
2. **Nunca** `java.`, `javax.` ou `org.jetbrains.skia.`. O autocomplete oferece essas opções e elas compilam no desktop, mas quebram no Android e na web. Se aparecer, está errado.

**Visual**

3. Cores **só** do tema: `MaterialTheme.colorScheme.xxx`. Nunca `Color(0xFF...)` dentro de uma tela.
4. Espaçamento (`padding`, `spacedBy`, `Spacer`) **só** com `Spacing.xs/sm/md/lg/xl/xxl/xxxl`. Tamanho e forma (largura de um card, raio de canto) podem usar `dp` direto.
5. Texto de botão com **só a primeira letra maiúscula**: "Tentar novamente", não "Tentar Novamente".
6. **Status** (disponível, atrasado, cancelada...) sempre com o `BadgeStatus`:
    ```kotlin
    BadgeStatus(emprestimo.status.descricao, corDoStatus(emprestimo.status))
    ```
   Não crie outro badge. A cor de cada status já está definida no `theme/Color.kt`, e cada cor tem um significado só no app inteiro (verde = tudo certo, azul = em andamento, âmbar = aguardando, vermelho = precisa de atenção, cinza = encerrado).
7. Antes de criar um componente, olhe em `ui/shared/`. Pode já existir: `EstadoDaTela`, `BadgeStatus`, `Avatar`, `AvatarAndDetails`, `AppTextField`.

**Componentes**

8. Todo `@Composable` que desenha algo recebe `modifier: Modifier = Modifier` como **primeiro parâmetro opcional** (depois dos obrigatórios) e repassa para o elemento de fora.
9. Dentro do componente, use `modifier` (minúsculo, o parâmetro). `Modifier` (maiúsculo) cria um novo e joga fora o que veio de quem chamou.

**Dados**

10. Nomes de campos e funções em **português**, como nos modelos (`nome`, `descricao`, `listar`).
11. Modelos são imutáveis. Para mudar algo, use `copy`:
    ```kotlin
    repository.atualizar(emprestimo.copy(status = StatusEmprestimo.FINALIZADO, dataDevolucao = hoje))
    ```
12. Datas são `kotlinx.datetime.LocalDate`. Para pegar a data de hoje:
    ```kotlin
    val hoje = Clock.System.todayIn(TimeZone.currentSystemDefault())
    ```
    O `Clock` vem de `kotlin.time`. Exemplos na internet com `kotlinx.datetime.Clock` são de uma versão antiga. Se o IntelliJ pedir `@OptIn(ExperimentalTime::class)`, aceite.

**Antes de mandar o código**

13. Se uma função ou parâmetro que você criou está **cinza** no IntelliJ, ninguém está usando. Quase sempre é porque você esqueceu de ligar em algum lugar.
14. Rode `Ctrl+Alt+L` (formatar) e `Ctrl+Alt+O` (limpar imports) no arquivo.
15. Rode o app e teste os quatro estados. Para testar o erro, coloque um `throw AppException("teste")` temporário no `listar()` do fake e **tire antes do commit**.

---

## 5. Git

1. **Nunca faça commit direto na `main`.**
2. Crie uma branch para a sua tela a partir da `main` atualizada:
   ```
   git checkout main
   git pull
   git checkout -b tela/exemplares
   ```
3. Faça commits pequenos, com mensagem dizendo o que mudou.
4. Antes de abrir o Pull Request, traga as novidades da `main`:
   ```
   git pull origin main
   ```
5. Abra o Pull Request para a `main`. O Gustavo revisa antes de entrar.