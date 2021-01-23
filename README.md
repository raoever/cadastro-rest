# cadastro-rest
API REST de Hotel

Além dos arquivos sqlCidades.sql e sqlEstados.sql diposnibilizados também foi incluído o arquivo splComplementar para a entrada dos dados de bairros, hoteis e quartos.

Também foi disponibilizado o arquivo SugestãoEndpointsREST (como o próprio nome já deixa implícido) que sugere alguns End Points para a utilização da API REST e explica alguns comportamentos da API.

# Sugestão de EndpointsREST

//    consultar hotéis por nome de cidade
Método GET
http://localhost:8080/api/hotel/NomeCidade/Gurupi

//    consultar hotéis por nome de cidade e estado para para cidades com nomes repetidos
Método GET
http://localhost:8080/api/hotel/NomeCidade/Palmas
Gera erro com a mensagem: Achado mais de uma cidade com o nome indicado, Por favor entre como nome da cidade + UF - ex.: Palmas/TO.
http://localhost:8080/api/hotel/NomeCidade/Palmas/TO

//    consultar hotéis por id de cidade
Método GET
http://localhost:8080/api/hotel/PorIdCidade/5514

//    consultar hotéis por nome de bairro de uma cidade
Método GET
http://localhost:8080/api/hotel/PorNomeCidadeBairro/Gurupi/Centro

//    consultar hotéis por nome de bairro de uma cidade e estado para cidades com nomes repetidos
Método GET
http://localhost:8080/api/hotel/PorNomeCidadeBairro/Palmas/Plano%20Diretor%20Sul
Gera erro com a mensagem: Achado mais de uma cidade com o nome indicado, Por favor entre como nome da cidade + UF - ex.: Palmas/TO/Plano%20Diretor%20Norte.
http://localhost:8080/api/hotelPorNomeCidadeBairro/Palmas/TO/Plano%20Diretor%20Norte

//    consultar hotéis por Id bairro de uma Id cidade
Método GET
http://localhost:8080/api/hotel/PorIdCidadeIdBairro/5514/2

//    consultar hotéis por cidade, faixa de preço e total de camas
Método GET
http://localhost:8080/api/hotel/CidadeFaixaCamas/Gurupi/100/300/2

//    consultar hotéis por cidade, uf, faixa de preço e total de camas
Método GET
http://localhost:8080/api/hotel/CidadeFaixaCamas/Palmas/100/300/2
Gera erro com mensagem: Achado mais de uma cidade com o nome indicado, Por favor entre como nome da cidade + UF - ex.: Palmas/TO/100/300/2.
http://localhost:8080/api/hoteisCidadeFaixaCamas/Palmas/TO/100/300/2

//    consultar hotéis por Id cidade, faixa de preço e total de camas
Método GET
http://localhost:8080/api/hotel/IdCidadeFaixaCamas/5514/50/200/2

//    cadastrar reserva de quarto
Método POST
http://localhost:8080/api/reserva/adiciona
JSON:
{
"nomeCliente": "Maria",
"checkin": "2021-01-01",
"checkout": "2021-01-31",
"quarto":{
  "id": 16
  }
}

//    alterar reserva de quarto
Método PUT
http://localhost:8080/api/reserva/altera
JSON:
{
"id": 1,
"nomeCliente": "João e Maria",
"checkin": "2021-01-01",
"checkout": "2021-01-31",
"quarto":{
  "id": 1
  }
}

//    excluir reserva de quarto
Método DELETE 
http://localhost:8080/api/reserva/exclui/1

OBSERVAÇÃO: Caso uma cidade, estado ou bairro não seja encontrado também é gerado um erro com a respectiva mensagem.
