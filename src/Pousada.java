import java.io.BufferedReader;
import java.io.*;

public class Pousada {
    private String nome;
    private String contato;
    private Quarto[] quartos;
    private Reserva[] reservas;
    private Produto[] produtos;
    private int nQuartos;


    Pousada(){
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
                        for (int i = j; i < this.nQuartos; i++) {
                            this.quartos[i]=new Quarto();
                            this.quartos[i].setNumero(Integer.parseInt(Quarto_atributos[j++]));
                            this.quartos[i].setCategoria(Quarto_atributos[j++].charAt(0));
                            this.quartos[i].setDiaria(Float.parseFloat(Quarto_atributos[j++]));
                            //this.quartos[i].setConsumo(new int[50]); //inicializa o vetor consumo com limite 50
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


}
