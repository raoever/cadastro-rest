# cadastro-rest
## API REST de Hotel - Projeto Final de Sistemas Distribuídos IFTO Palmas

Além dos arquivos sqlCidades.sql e sqlEstados.sql diposnibilizados também foi incluído o arquivo splComplementar para a entrada dos dados de bairros, hoteis e quartos.



# Sugestão de EndpointsREST para a utilização da API REST e explicação de alguns comportamentos da API.

## Consultar hotéis por nome de cidade
Método GET </br>
http://localhost:8080/api/hotel/NomeCidade/Gurupi

## Consultar hotéis por nome de cidade e estado para para cidades com nomes repetidos
Método GET </br>
http://localhost:8080/api/hotel/NomeCidade/Palmas
Gera erro com a mensagem: Achado mais de uma cidade com o nome indicado, Por favor entre como nome da cidade + UF - ex.: Palmas/TO.
http://localhost:8080/api/hotel/NomeCidade/Palmas/TO

## Consultar hotéis por id de cidade
Método GET </br>
http://localhost:8080/api/hotel/PorIdCidade/5514

## Consultar hotéis por nome de bairro de uma cidade
Método GET </br>
http://localhost:8080/api/hotel/PorNomeCidadeBairro/Gurupi/Centro

## Consultar hotéis por nome de bairro de uma cidade e estado para cidades com nomes repetidos
Método GET </br>
http://localhost:8080/api/hotel/PorNomeCidadeBairro/Palmas/Plano%20Diretor%20Sul
Gera erro com a mensagem: Achado mais de uma cidade com o nome indicado, Por favor entre como nome da cidade + UF - ex.: Palmas/TO/Plano%20Diretor%20Norte.
http://localhost:8080/api/hotelPorNomeCidadeBairro/Palmas/TO/Plano%20Diretor%20Norte

## Consultar hotéis por Id bairro de uma Id cidade
Método GET </br>
http://localhost:8080/api/hotel/PorIdCidadeIdBairro/5514/2

## Consultar hotéis por cidade, faixa de preço e total de camas
Método GET </br>
http://localhost:8080/api/hotel/CidadeFaixaCamas/Gurupi/100/300/2

## Consultar hotéis por cidade, uf, faixa de preço e total de camas
Método GET </br>
http://localhost:8080/api/hotel/CidadeFaixaCamas/Palmas/100/300/2
Gera erro com mensagem: Achado mais de uma cidade com o nome indicado, Por favor entre como nome da cidade + UF - ex.: Palmas/TO/100/300/2.
http://localhost:8080/api/hoteisCidadeFaixaCamas/Palmas/TO/100/300/2

## Consultar hotéis por Id cidade, faixa de preço e total de camas
Método GET </br>
http://localhost:8080/api/hotel/IdCidadeFaixaCamas/5514/50/200/2

## Cadastrar reserva de quarto
Método POST </br>
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

## Alterar reserva de quarto
Método PUT </br>
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

## Excluir reserva de quarto
Método DELETE  </br>
http://localhost:8080/api/reserva/exclui/1

## OBSERVAÇÃO: Caso uma cidade, estado ou bairro não seja encontrado também é gerado um erro com a respectiva mensagem.
