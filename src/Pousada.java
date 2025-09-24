import java.io.BufferedReader;
import java.util.Arrays;
import java.io.*;

public class Pousada {
    private String nome;
    private String contato;
    private Quarto[] quartos;
    private Reserva[] reservas;
    private Produto[] produtos;
    private int nQuartos;

    Pousada(){
        this.nome="NULO";
        this.contato="NULO";
        this.nQuartos=0;
        carregaDados();
    }

    public String getNome() {
        return this.nome;
    }
    public String getContato() {
        return this.contato;
    }
    public Quarto[] getQuartos() {
        return this.quartos;
    }
    public Reserva[] getReservas() {
        return this.reservas;
    }
    public Produto[] getProdutos() {
        return this.produtos;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setContato(String contato) {
        this.contato = contato;
    }
    public void setQuartos(Quarto[] quartos) {
        this.quartos = quartos;
    }
    public void setReservas(Reserva[] reservas) {
        this.reservas = reservas;
    }
    public void setProdutos(Produto[] produtos) {
        this.produtos = produtos;
    }

    public boolean consultaDisponibilidade(int data, int numeroQuarto) {
        for (Reserva r : this.reservas) {
            if (r.getQuarto().getNumero() == numeroQuarto && 
                (r.getStatus() == 'A' || r.getStatus() == 'I') &&
                data >= r.getDiaInicio() && data <= r.getDiaFim()) {
                return false;
            }
        }
        return true;
    }

    public Reserva[] consultaReserva(int data, String cliente, int numeroQuarto) {
        Reserva[] resultados = new Reserva[10];
        int count = 0;
        
        for (Reserva r : this.reservas) {
            if (r.getStatus() == 'A') {
                boolean corresponde = true;
                
                if (data < r.getDiaInicio() || data > r.getDiaFim()) {
                    corresponde = false;
                }
                
                if (!r.getCliente().equalsIgnoreCase(cliente)) {
                    corresponde = false;
                }
                
                if (r.getQuarto().getNumero() != numeroQuarto) {
                    corresponde = false;
                }
                
                if (corresponde) {
                    if (count < resultados.length) {
                        resultados[count] = r;
                        count++;
                    } else {
                        Reserva[] novoArray = Arrays.copyOf(resultados, resultados.length * 2);
                        novoArray[count] = r;
                        resultados = novoArray;
                        count++;
                    }
                }
            }
        }
        
        return Arrays.copyOf(resultados, count);
    }

    public boolean realizaReserva(int diaInicio, int diaFim, String cliente, int numeroQuarto) {
        Quarto quartoDesejado = null;
        for (Quarto q : this.quartos) {
            if (q.getNumero() == numeroQuarto) {
                quartoDesejado = q;
                break;
            }
        }
        
        if (quartoDesejado == null) {
            System.out.println("Quarto não encontrado.");
            return false;
        }
        boolean clienteTemReserva = false;
        for (Reserva r : this.reservas) {
            if (r.getCliente().equals(cliente) && 
                (r.getStatus() == 'A' || r.getStatus() == 'I')) {
                clienteTemReserva = true;
            }
        }
        if(clienteTemReserva){
            System.out.println("Cliente já possui reserva ativa ou em check-in");
            return false;
        }
        boolean disponivel = true;
        for (int dia = diaInicio; dia <= diaFim; dia++) {
            if (!consultaDisponibilidade(dia, numeroQuarto)) {
                disponivel = false;
            }
        }
        if (!disponivel) {
            System.out.println("Quarto não disponível para o período solicitado.");
            return false;
        }


        Reserva novaReserva = new Reserva();
        novaReserva.setDiaInicio(diaInicio);
        novaReserva.setDiaFim(diaFim);
        novaReserva.setCliente(cliente);
        novaReserva.setQuarto(quartoDesejado);
        novaReserva.setStatus('A');

        if (this.reservas == null) {
            this.reservas = new Reserva[1];
            this.reservas[0] = novaReserva;
        } else {
            boolean adicionada = false;
            for (int i = 0; i < this.reservas.length; i++) {
                if (this.reservas[i] == null) {
                    this.reservas[i] = novaReserva;
                    adicionada = true;
                    break;
                }
            }
            
            if (!adicionada) {
                Reserva[] novoArray = Arrays.copyOf(this.reservas, this.reservas.length + 1);
                novoArray[this.reservas.length] = novaReserva;
                this.reservas = novoArray;
            }
        }

        return true;
    }

    public boolean cancelaReserva(String cliente) {
        if (this.reservas == null) {
            return false;
        }
        
        for (Reserva r : this.reservas) {
            if (r != null && r.getCliente().equalsIgnoreCase(cliente) && r.getStatus() == 'A') {
                r.setStatus('C'); // Muda o status para Cancelada
                return true;
            }
        }
        
        return false; // Não encontrou reserva ativa para o cliente
    }

    public void realizaCheckIn(String cliente) {
        if (this.reservas == null) {
            System.out.println("Nenhuma reserva encontrada.");
            return;
        }
        
        for (Reserva r : this.reservas) {
            if (r != null && r.getCliente().equalsIgnoreCase(cliente) && r.getStatus() == 'A') {
                r.setStatus('I'); // Muda o status para Check-In
                System.out.println("Check-in realizado com sucesso para " + cliente);
                System.out.println("Detalhes da reserva: " + r.toString());
                return;
            }
        }
        
        System.out.println("Nenhuma reserva ativa encontrada para o cliente: " + cliente);
    }
    public void realizaCheckOut(String cliente) {
        if (this.reservas == null) {
            System.out.println("Nenhuma reserva encontrada.");
            return;
        }
        
        for (Reserva r : this.reservas) {
            if (r != null && r.getCliente().equalsIgnoreCase(cliente) && r.getStatus() == 'I') {
                r.setStatus('O'); // Muda o status para Check-Out
                r.getQuarto().limpaConsumo(); //Limpa o consumo do quarto
                System.out.println("Check-out realizado com sucesso para " + cliente);
                System.out.println("Detalhes da reserva: " + r.toString());
                return;
            }
        }
        
        System.out.println("Nenhuma reserva com check-in encontrado para o cliente: " + cliente);
    }

    public void carregaDados(){
        File arqPousada = new File("pousada.txt");
        try {
            if(arqPousada.exists()){ //verifica se o arquivo existe
                BufferedReader brPousadaTxt = new BufferedReader(new FileReader(arqPousada));
                String[] dados = brPousadaTxt.readLine().split(";");//Divide os atributos separados por ; e coloca em um vetor de strings
                setNome(dados[0]); //primeiro atributo é o nome
                setContato(dados[1]);//segundo atributo é o telefone de contato
                this.nQuartos = brPousadaTxt.readLine().split(";").length; //lê a segunda linha do arquivo para contar o número de quartos
                carregaQuartos();
                carregaReservas();
                carregaProdutos();
                System.out.println("Pousada carregada com sucesso.");
                brPousadaTxt.close();
            } else {
                System.out.println("Arquivo da pousada não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao acessar arquivo da pousada.");
            e.printStackTrace();
        }
    }
    private void carregaReservas(){
        File arqReservas = new File("reserva.txt");
        try{
            if(arqReservas.exists()){//verifica se o arquivo existe
                BufferedReader brReservasTxt = new BufferedReader(new FileReader(arqReservas));
                String linha=brReservasTxt.readLine(); //tenta ler a primeira linha do arquivo
                if(linha==null){
                    System.out.println("Arquivo de reservas vazio.");
                    brReservasTxt.close();
                    return;
                }
                String Reserva_atributos[]=linha.split(";");//Divide os atributos separados por ; e coloca em um vetor de strings
                this.reservas = new Reserva[5];//inicializa o vetor de reservas com limite 5
                int nReservas=0; //contador para o número de reservas lidas
                int j=0;//contador para percorrer o vetor de atributos em cada linha
                for (int i = 0; i < this.reservas.length; i++) {
                    this.reservas[i]=new Reserva();
                    this.reservas[i].setDiaInicio(Integer.parseInt(Reserva_atributos[j++]));
                    this.reservas[i].setDiaFim(Integer.parseInt(Reserva_atributos[j++]));
                    this.reservas[i].setCliente(Reserva_atributos[j++]);
                    for(Quarto q: this.quartos){ //Procura o quarto correspondente ao número lido
                        if(q.getNumero() == Integer.parseInt(Reserva_atributos[j])){
                            this.reservas[i].setQuarto(q);
                            this.reservas[i].setStatus(Reserva_atributos[++j].charAt(0));
                            break;
                        }
                    }
                    nReservas++;
                    if(nReservas == this.reservas.length){ //se o vetor estiver cheio
                        this.reservas = Arrays.copyOf(this.reservas, nReservas*2); //redimensiona o vetor reservas para o dobro do tamanho
                    }
                    linha=brReservasTxt.readLine();//tenta ler a próxima linha do arquivo
                    if(linha==null) break; //se a linha for nula, sai do loop
                    else Reserva_atributos=linha.split(";");
                    j=0;
                }
                this.reservas = Arrays.copyOf(this.reservas, nReservas); //ajusta o tamanho do vetor ao número de reservas lidas
                System.out.println("Reservas carregadas com sucesso.");
                brReservasTxt.close();
            }
            else{
                System.out.println("Arquivo de reservas não encontrado.");
            }
        }catch(Exception e){   
            System.out.println("Erro ao acessar arquivo de reservas.");
            e.printStackTrace();
        }
    }
    private void carregaQuartos(){
        File arqQuartos = new File("quarto.txt");
        try{
            if(arqQuartos.exists()){//verifica se o arquivo existe
                BufferedReader brQuartosTxt = new BufferedReader(new FileReader(arqQuartos));
                String linha=brQuartosTxt.readLine(); //tenta ler a primeira linha do arquivo
                if(linha==null){
                    System.out.println("Arquivo de quartos vazio.");
                    brQuartosTxt.close();
                    return;
                }
                String Quarto_atributos[]=linha.split(";");//Divide os atributos separados por ; e coloca em um vetor de strings
                this.quartos = new Quarto[this.nQuartos];//inicializa o vetor de quartos com o número de quartos lido
                int j=0;//contador para percorrer o vetor de atributos em cada linha
                for (int i = 0; i < this.nQuartos; i++) {
                    this.quartos[i]=new Quarto();
                    this.quartos[i].setNumero(Integer.parseInt(Quarto_atributos[j++]));
                    this.quartos[i].setCategoria(Quarto_atributos[j++].charAt(0));
                    this.quartos[i].setDiaria(Float.parseFloat(Quarto_atributos[j++]));
                    //String teste = Quarto_atributos[j];
                    String VetorConsumo[] = Quarto_atributos[j].split(",");
                    System.out.println("Consumo lido: " + Arrays.toString(VetorConsumo));
                    
                    int[] codigos = new int[(VetorConsumo.length)];
                    int k=0;
                    for(String cod : VetorConsumo){
                        if(Integer.parseInt(cod)!=0)
                            codigos[k++] = Integer.parseInt(cod);

                    }
                    this.quartos[i].setConsumo(codigos);
                    
                    
                    

                    
                    linha=brQuartosTxt.readLine();//tenta ler a próxima linha do arquivo
                    if(linha==null) break; //se a linha for nula, sai do loop
                    else Quarto_atributos=linha.split(";");
                    j=0;
                }
                System.out.println("Quartos carregados com sucesso.");
                brQuartosTxt.close();
            }
            else{
                System.out.println("Arquivo de quartos não encontrado.");
            }
        }catch(Exception e){   
            System.out.println("Erro ao acessar arquivo de quartos.");
            e.printStackTrace();
        }
    }
    private void carregaProdutos(){
        File arqProdutos = new File("produto.txt");
        try{
            if(arqProdutos.exists()){//verifica se o arquivo existe
                BufferedReader brProdutosTxt = new BufferedReader(new FileReader(arqProdutos));
                String linha=brProdutosTxt.readLine(); //tenta ler a primeira linha do arquivo
                if(linha==null){
                    System.out.println("Arquivo de produtos vazio.");
                    brProdutosTxt.close();
                    return;
                }
                String Produto_atributos[]=linha.split(";");//Divide os atributos separados por ; e coloca em um vetor de strings
                this.produtos = new Produto[5];//inicializa o vetor de produtos com limite 5
                int nProdutos=0; //contador para o número de produtos lidos
                int j=0;//contador para percorrer o vetor de atributos em cada linha
                for (int i = 0; i < this.produtos.length; i++) {
                    this.produtos[i]=new Produto(); 
                    this.produtos[i].setCodigo(Integer.parseInt(Produto_atributos[j++]));
                    this.produtos[i].setNome(Produto_atributos[j++]);
                    this.produtos[i].setPreco(Float.parseFloat(Produto_atributos[j++]));
                    nProdutos++;
                    if(nProdutos == this.produtos.length){ //se o vetor estiver cheio
                        this.produtos = Arrays.copyOf(this.produtos, nProdutos*2); //redimensiona o vetor produtos para o dobro do tamanho
                    }
                    linha=brProdutosTxt.readLine();//tenta ler a próxima linha do arquivo
                    if(linha==null) break; //se a linha for nula, sai do loop
                    else Produto_atributos=linha.split(";");
                    j=0;
                }
                this.produtos = Arrays.copyOf(this.produtos, nProdutos); //ajusta o tamanho do vetor ao número de produtos lidos
                System.out.println("Produtos carregados com sucesso.");
                brProdutosTxt.close();
            }
            else{
                System.out.println("Arquivo de produtos não encontrado.");
            }
        }catch(Exception e){   
            System.out.println("Erro ao acessar arquivo de produtos.");
            e.printStackTrace();
        }
    }
    public void salvaDados(){
        File arqReservas = new File("reserva2.txt");
        try {
            BufferedWriter bwReservasTxt = new BufferedWriter(new FileWriter(arqReservas));
            if(this.reservas != null){
                for(int i=0; i<this.reservas.length; i++){
                    if(this.reservas[i] != null){
                        if(this.reservas[i].getStatus() != ('C' | 'O')){ //Salva apenas reservas que não foram canceladas ou fizeram check-out
                            bwReservasTxt.write(this.reservas[i].getDiaInicio()+";"+this.reservas[i].getDiaFim()+";"+this.reservas[i].getCliente()+";"+this.reservas[i].getQuarto().getNumero()+";"+this.reservas[i].getStatus());
                            bwReservasTxt.newLine();
                        }
                        
                    }
                }
                System.out.println("Arquivo de reservas salvo com sucesso. Nome do arquivo: " + arqReservas.getName());
            }
            bwReservasTxt.close();
        } catch (Exception e) {
            System.out.println("Erro ao salvar arquivo de reservas.");
            e.printStackTrace();
        }

        File arqQuartos = new File("quarto2.txt");
        try {
            BufferedWriter bwQuartosTxt = new BufferedWriter(new FileWriter(arqQuartos));
            if(this.quartos != null){ //Testa se existem quartos
                for(int i=0; i<this.quartos.length; i++){
                    if(this.quartos[i] != null){
                        bwQuartosTxt.write(this.quartos[i].getNumero()+";"+this.quartos[i].getCategoria()+";"+(int)this.quartos[i].getDiaria()+";"); // Escreve os 3 primeiros atributos do quarto                     
                        for(int j=0; j<this.quartos[i].getConsumo().length; j++){// Escreve o vetor de consumo do quarto
                            if(this.quartos[i].getConsumo()[j] != 0) //Não salva códigos nulos
                                bwQuartosTxt.write(Integer.toString(this.quartos[i].getConsumo()[j]));
                            if(j != this.quartos[i].getConsumo().length - 1){
                                bwQuartosTxt.write(","); //Adiciona vírgula entre os códigos, mas não no final
                            }
                        }
                        bwQuartosTxt.newLine();
                    }
                }
            }
            bwQuartosTxt.close();
        } catch (Exception e) {
            System.out.println("Erro ao salvar arquivo de quartos.");
            e.printStackTrace();
        }
    }
}