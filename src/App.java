import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Pousada pousada = new Pousada(); //Inicializa a pousada, seu construtor carrega os dados dos arquivos
        System.out.println(pousada.getNome()); //"NULO" se o arquivo não for encontrado
        for(Quarto q: pousada.getQuartos()){
            System.out.println(q.toString());
        }
        for(Reserva r: pousada.getReservas()){
            System.out.println(r.toString());
        }
        Scanner scanner = new Scanner(System.in);
        int codigo=0; //Precisa declarar fora do loop pra poder usar no while
        do{
            System.out.println("Bem vindo à Pousada Pou Usada!"); //TODO: Pensar num trocadilho melhor.
            System.out.println("Selecione a opção desejada: MÊS:DEZEMBRO/2025"); //mês podia ser uma variável;
            System.out.println("1 - Consultar Disponibilidade");
            System.out.println("2 - Consultar Reserva");
            System.out.println("3 - Realizar Reserva");
            System.out.println("4 - Cancelar Reserva");
            System.out.println("5 - Realizar Check-in");
            System.out.println("6 - Realizar Check-out");
            System.out.println("7 - Registrar Consumo");
            System.out.println("8 - Salvar"); 
            System.out.println("0 - Sair");
            System.out.printf("Digite o número da opção: ");
            codigo=scanner.nextInt();
            scanner.nextLine(); //consome a quebra de linha deixada pelo nextInt() //TODO: Perguntar se existe alternativa porque foi o chatgpt que sugeriu
            switch (codigo) {
                case 1:

                    break;
                case 7:
                    System.out.println("Clientes que já fizeram check-in:");
                    for(Reserva r : pousada.getReservas()){
                        if(r.getStatus()=='I') //Mostra apenas reservas com check-in realizado
                            System.out.println(r.getCliente()+" - Quarto "+r.getQuarto().getNumero() + ", de dias "+r.getDiaInicio()+" a "+r.getDiaFim());
                    }
                    System.out.println("Digite o nome do cliente:");
                    String cliente = scanner.nextLine();
                    boolean clienteEncontrado = false;
                    for(Reserva r: pousada.getReservas()){
                        if(r.getCliente().equals(cliente)){ //Checa se o nome do cliente bate com alguma reserva
                            switch (r.getStatus()) {
                                case 'I':
                                    for(Produto p: pousada.getProdutos()){ //Mostra a lista de produtos disponíveis
                                        System.out.println(p.toString());
                                    }
                                    System.out.println("Digite o código do produto consumido, 0 para sair:");
                                    int codProduto = scanner.nextInt();
                                    boolean produtoValido = false;
                                    do{
                                        for(Produto p: pousada.getProdutos()){
                                            if(p.getCodigo()==codProduto){
                                                r.getQuarto().adicionaConsumo(codProduto);
                                                System.out.println(p.getNome()+" - R$ "+p.getPreco()+" registrado com sucessoo.");
                                                produtoValido = true;
                                            }
                                        }
                                        if(!produtoValido && codProduto!=0){
                                            System.out.println("Código inválido, tente novamente.");
                                        }
                                        produtoValido = false; //reinicia a variável para a próxima iteração
                                    }while((codProduto=scanner.nextInt())!=0); //Lê os códigos dos produtos consumidos até o usuário digitar 0
                                    break;
                                case 'O':
                                    System.out.println("O cliente já realizou check-out.");
                                    break;
                                case 'A':
                                    System.out.println("O cliente ainda não realizou check-in.");
                                    break;
                                case 'C':
                                    System.out.println("O cliente cancelou sua reserva.");
                                    break;
                                default:
                                    break;
                            }
                            clienteEncontrado = true;
                        }
                    }
                    if(!clienteEncontrado){ //Mostra a mensagem apenas uma vez
                        System.out.println("Cliente não encontrado.");
                    }
                    break;
            
                default:
                    break;
            }
        } while(codigo != 0);
        scanner.close();
        /* for(Quarto q: pousada.getQuartos()){ //Mostra os atributos dos quartos e seus consumos
            System.out.println(q.toString());
            if(q.getConsumo().length>0){//Se o quarto tiver consumos registrados
                System.out.println("Consumo do quarto "+q.getNumero()+":");
                q.listaConsumo(pousada.getProdutos());
                q.valorTotalConsumo(pousada.getProdutos());
            }
        } */
    }
    
}
