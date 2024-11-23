# Projeto Sistema Integrado de Gestão Acadêmica
Este é um projeto acadêmico desenvolvido na disciplina de Linguagem de Programação - Java, que pretende ser um Sistema Integrado de Gestão Acadêmica, utilizando os princípios da POO e os recursos disponiveis na linguagem JAVA, o mesmo possui conexão com banco de dados Postgres e uma interface visual feita com JavaFX.
<br/>
## Integrantes
<table align="center">
  <tr>
    <td align="center">
      <a href="https://github.com/Caio-Sdk8"><img src="https://avatars.githubusercontent.com/u/82384954?v=4" alt="Caio Soares" style="border-radius: 50%; width: 10vw;"></a>
      <br />
      <b>Caio Soares</b>
      <br />
      Desenvolvedor
    </td>
    <td align="center">
      <a href="https://github.com/matheuskkg"><img src="https://avatars.githubusercontent.com/u/148032884?v=4" alt="Matheus Graciliano" style="border-radius: 50%; width: 10vw;"></a>
      <br />
      <b>Matheus Graciliano</b>
      <br />
      Desenvolvedor
    </td>
    <td align="center">
      <a href="https://github.com/Daniel120904"><img src="https://avatars.githubusercontent.com/u/183426215?v=4" alt="Daniel Samayoa" style="border-radius: 50%; width: 10vw;"></a>
      <br />
      <b>Daniel Samayoa</b>
      <br />
      Desenvolvedor
    </td>
    <td align="center">
      <a href="https://github.com/1rg0"><img src="https://avatars.githubusercontent.com/u/54910774?v=4" alt="Igor Fernandes" style="border-radius: 50%; width: 10vw;"></a>
      <br />
      <b>Igor Fernandes</b>
      <br />
      Desenvolvedor
    </td>
      <td align="center">
      <a href="https://github.com/lukshima"><img src="https://avatars.githubusercontent.com/u/183426312?v=4" alt="Lucas Nakashima" style="border-radius: 50%; width: 10vw;"></a>
      <br />
      <b>Lucas Nakashima</b>
      <br />
      Desenvolvedor
    </td>
    </td>
      <td align="center">
      <a href="https://github.com/joaovkk"><img src="https://avatars.githubusercontent.com/u/174809913?v=4" alt="João Santos" style="border-radius: 50%; width: 10vw;"></a>
      <br />
      <b>João Santos</b>
      <br />
      Desenvolvedor
    </td>
  </tr>
</table>
<br/>

## Estrutura de pastas
- **siga-javafx/**: Diretório principal.
  - **src/**: Contém diretórios e códigos essenciais.
    - **main/**: Contém diretórios de código e recursos/configurações.
      - **java/**: Contém acesso aos códigos e arquivo de dependências.
        - **fatec.sigafx/**: Código fonte.
            - **controller/**: Contém os controladores do sistema, responsáveis por parte das funções e integração com a parte visual da aplicação.
            - **dao/**: Contém funções de conexão com o banco de dados de cada uma das classes.
            - **model/**: Contém todas as models (classes/entidades) do sistema.
            - **util/**: Contém funções utilitárias utilizadas nas outras classes.  
            - **view/**: Contém uma configuração e um controle da parte visual da aplicação.
            - `EMF`: Arquivo de criação do gerenciador de entidades.
            - `SigaApplication`: Core da aplicação.
        - `module-info.java`: dependências da aplicação
      - **resources/**: Contém códigos.
        - **css/**: Arquivos de estilização da interface visual.
        - **fxml/**: Arquivos de construção da interface visual, semelhante ao html.
        - **icons/**: Imagens utilizadas na interface.
        - **META-INF/**: Arquivo para conexão com banco de dados.
<br/>

## Como testar nosso projeto localmente
Confira abaixo o passo-a-passo necessário para rodar o nosso projeto localmente na sua máquina. caso algum erro ocorra, sinta-se livre para comunicar qualquer um dos integrantes, estamos dispostos a ajudar você a utilizar/testar nosso sistema.

### Configurando o ambiente
Antes de clonar nosso repositório, garanta que você tenha os programas abaixo:
+ [IntelliJ](https://www.jetbrains.com/idea/download/?section=windows)* (recomendamos o uso de intellij principalmente pelo fato de caso esteja usando uma versão antiga do java ou não tenha ele instalado, a ide fará a instalação por você, caso permita)
+ [Git](https://git-scm.com/downloads) ou GitHub Desktop
+ [Postgres](https://www.postgresql.org/download/) <br/><br/>
*Caso faça parte de alguma instituição de ensino, use seu e-mail institucional para criar um acesso community, podendo assim usar a IDE para além do teste grátis.
<br/>

Com todos os programas acima instalados, siga o passo-a-passo abaixo:
+ No PgAdmin crie um banco de dados chamado "siga"
+ Usando o git, ou github desktop, clone nosso repositório
+ Abra o arquivo persistence.xml (../siga-javafx/src/main/resources/META-INF/persistence.xml)
+ Dentro do arquivo procure as tags abaixo e preencha com suas informações:
+ ```
            <property name="jakarta.persistence.jdbc.user" value="seuUsuario"/>
            <property name="jakarta.persistence.jdbc.password" value="suaSenha"/>
+ Após esses passos rode o programa indo no arquivo SigaApplication (../siga-javafx/src/main/java/fatec.sigafx/SigaApplication)
+ Em caso de erro, sinta-se a vontade para notificar qualquer um dos integrantes, estamos dispostos a te ajudar.








