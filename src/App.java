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
                    System.out.println("Clientes com reservas ativas:");
                    for(Reserva r : pousada.getReservas()){
                        if(r.getStatus()=='A') //Mostra apenas reservas ativas
                            System.out.println(r.getCliente());
                    }
                    System.out.println("Digite o nome do cliente:");
                    String cliente = scanner.nextLine();
                    boolean clienteEncontrado = false;
                    for(Reserva r: pousada.getReservas()){
                        if(r.getCliente().equals(cliente)){
                            for(Produto p: pousada.getProdutos()){ //Mostra a lista de produtos disponíveis
                                System.out.println(p.toString());
                            }
                            System.out.println("Digite o código do produto consumido:");
                            int codProduto = scanner.nextInt();
                            r.getQuarto().adicionaConsumo(codProduto);
                            System.out.println("Consumo registrado com sucesso!");
                            clienteEncontrado = true;
                        }
                    }
                    if(!clienteEncontrado){
                        System.out.println("Cliente não encontrado.");
                    }
                    break;
            
                default:
                    break;
            }
        } while(codigo != 0);
        scanner.close();
        for(Quarto q: pousada.getQuartos()){
            System.out.println(q.toString());
        }
    }
    
}
