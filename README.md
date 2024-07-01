Leitura de banco postgres com Spring batch 
 
 Paginador X cursor

 com Paginador tem a desvantagem de ser mais lento pois le o banco de dados em varias "paginas" etapas , porem nao tem risco de estourar a memoria pois mesmo um banco muito grande ele ira ler em Paginas/etapas
 com cursor tem a vantagem de ser mais rapido pois le o banco em uma unica vez , porem tem risco de estourar a memoria pois lendo o banco uma unic vez e o banco ser muito grande ocupara muita memoria para processar essa leitura
