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
                    this.reservas[i]=new Reserva(); //TODO: Perguntar porque não funciona sem isso
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
                    this.quartos[i]=new Quarto(); //TODO: Perguntar porque não funciona sem isso
                    this.quartos[i].setNumero(Integer.parseInt(Quarto_atributos[j++]));
                    this.quartos[i].setCategoria(Quarto_atributos[j++].charAt(0));
                    this.quartos[i].setDiaria(Float.parseFloat(Quarto_atributos[j++]));
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
                    this.produtos[i]=new Produto(); //TODO: Perguntar porque não funciona sem isso
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


}
