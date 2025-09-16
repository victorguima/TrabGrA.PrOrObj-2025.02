import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Pousada pousada = new Pousada();
        System.out.println(pousada.getNome());
        for(Quarto q: pousada.getQuartos()){
            System.out.println(q.toString());
        }
        for(Reserva r: pousada.getReservas()){
            System.out.println(r.toString());
        }
        Scanner scanner = new Scanner(System.in);
        int codigo=0;
        do{
            System.out.println("Bem vindo à Pousada Pou Usada!");
            System.out.println("Selecione a opção desejada: MÊS:DEZEMBRO/2025");
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
            scanner.nextLine();
            switch (codigo) {
                case 1:
                    System.out.println("Digite o dia (1-31):");
                    int dia = scanner.nextInt();
                    System.out.println("Digite o número do quarto:");
                    int numQuarto = scanner.nextInt();
                    scanner.nextLine();
                    pousada.consultaDisponibilidade(dia, numQuarto);
                    break;
                case 4:
                    System.out.println("Digite o nome do cliente:");
                    String clienteCancelar = scanner.nextLine();
                    pousada.cancelaReserva(clienteCancelar);
                    break;
                case 7:
                    System.out.println("Clientes com reservas ativas:");
                    for(Reserva r : pousada.getReservas()){
                        if(r.getStatus()=='A')
                            System.out.println(r.getCliente());
                    }
                    System.out.println("Digite o nome do cliente:");
                    String cliente = scanner.nextLine();
                    boolean clienteEncontrado = false;
                    for(Reserva r: pousada.getReservas()){
                        if(r.getCliente().equals(cliente)){
                            for(Produto p: pousada.getProdutos()){
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
