

# id, nome, id_bairro
'1', 'Ibis Palmas', '1'
'2', 'Hotel Girassol Plaza', '1'
'3', 'Céu Palmas Hotel', '2'
'4', 'Hplus Premium Palmas\nHplus Premium Palmas\n', '2'
'5', 'Di Napoli Plaza Hotel', '3'
'6', 'Hotel MuHotel Mutucão\ntucão\n', '4'
'7', 'SerranSerranos Park Hotel\nos Park Hotel\n', '5'
'8', 'Hotel Entre Rios', '5'
'9', 'Hotel São Vicente', '6'
'10', 'Brii Hotel', '7'


# id, nome, id_cidade
'1', 'Plano Diretor Norte', '5514'
'2', 'Plano Diretor Sul', '5514'
'3', 'Centro', '5484'
'4', 'Jardim das Bandeiras', '5484'
'5', 'Centro', '5518'
'6', 'Centro', '5437'
'7', 'Setor Oeste', '5437'


id, andar, numero, numero_camas, preco, id_hotel
1,"1","101",2,150,1
2,"1","102",2,150,1
3,"1","103",1,100,1
4,"1","104",1,100,1
5,"2","201",2,150,1
6,"2","202",2,150,1
7,"2","203",1,100,1
8,"2","204",1,100,1
9,"3","301",3,300,1
10,"3","302",3,300,1
11,"3","303",1,250,1
12,"3","304",1,250,1
13,"4","401",4,400,1
14,"5","501",1,500,1

# id, andar, numero, numero_camas, preco, id_hotel
'1', '1', '101', '2', '150', '1'
'2', '1', '102', '2', '150', '1'
'3', '1', '103', '1', '100', '1'
'4', '1', '104', '1', '100', '1'
'5', '2', '201', '2', '150', '1'
'6', '2', '202', '2', '150', '1'
'7', '2', '203', '1', '100', '1'
'8', '2', '204', '1', '100', '1'
'9', '3', '301', '3', '300', '1'
'10', '3', '302', '3', '300', '1'
'11', '3', '303', '1', '250', '1'
'12', '3', '304', '1', '250', '1'
'13', '4', '401', '4', '400', '1'
'14', '5', '501', '1', '500', '1'
'15', '1', '101', '2', '150', '2'
'16', '1', '102', '2', '150', '2'


GetMapping("/hotelPorCidadeBairro/{id1}/{id2}/{id3}/{id4}")
    public List<Hotel> listHotelPorCidadeBairro(@PathVariable long id1, @PathVariable double id2, @PathVariable double id3, @PathVariable int id4) {
        return hotelDAO.listHoteisCidadeBairro(id1, id4);
    }


    {
"id": 24,
"nomeCliente": "Maria",
"checkin": "2021-01-01",
"checkout": "2021-01-31",
"quarto":{
  "id": 1
  }
}