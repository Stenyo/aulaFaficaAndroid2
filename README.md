### **Requisição em api** ###

- __Lista de repositórios__. Exemplo de chamada na API: `https://api.github.com/search/repositories?q=language:Java&sort=stars&page=1`
  * Paginação na tela de lista, com endless scroll / scroll infinito (incrementando o parâmetro `page`).
  * Cada repositório deve exibir Nome do repositório, Descrição do Repositório, Nome / Foto do autor, Número de Stars, Número de Forks
  * Ao tocar em um item, deve levar a lista de Pull Requests do repositório
- __Pull Requests de um repositório__. Exemplo de chamada na API: `https://api.github.com/repos/<criador>/<repositório>/pulls`
  * Cada item da lista deve exibir Nome / Foto do autor do PR, Título do PR, Data do PR e Body do PR
  * Ao tocar em um item, deve abrir no browser a página do Pull Request em questão

### **A solução de dependencias** ##
* Sistema de build Gradle
* Mapeamento JSON -> Objeto (uso do GSON)
* Material Design -> Foi utilizada a bibioteca do Cardview para a listagem

### **Requisitos não funcionais** ###

* Framework para comunicação com API (utilizado o Retrofit2 juntamente com o gson)
* Cache de imagens e da API (com.squareup.okhttp3:okhttp Cache)
* Suportar mudanças de orientação das telas sem perder estado (Inclusive com scroll infinito na tela principal)

### **EXTRAS** ###
 
* Uso do constraint-layout dedixando as telas totalmente responsivas sem a nescessidade de varios layouts para resoluções diferentes
* Uso do recyclerview  para reaproveitamento de telas no android e cache tornando o app mais otimizado e de melhor manutenção
* butterknife e notações android para um codigo muito mais limpo
* glide para trabalhar com imagens, widget RoundedImageView de arrendodamento de imagem.
* Cardview para material Design.
* uso do iconify para icones ou seja todos os icones disponibilisados pelo fontawesome utilizados como strings, sem a nescessidade de assets
* O principal extra deste codigo é a estrutura utilizada, com o padrão Singleton e o inject, mostro de forma simples como interceptar e injetar dependencias em
todas as requisiçoes da aplicação, sem a nescessidade de grandes mudanças, como adicionar token ou renovar token ou qualquer mudança de estrutura 
da requisição. 
