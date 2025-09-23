import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Pousada pousada = new Pousada(); //Inicializa a pousada, seu construtor carrega os dados dos arquivos
        
        Scanner scanner = new Scanner(System.in);
        int codigo=0; //Precisa declarar fora do loop pra poder usar no while
        do{
            System.out.println("Bem vindo à Pousada Pou Usada!"); //TODO: Pensar num trocadilho melhor.
            System.out.println("Selecione a opção desejada: DIA:DEZEMBRO/2025"); //mês podia ser uma variável;
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
                // FUNÇÃO 1: CONSULTAR DISPONIBILIDADE
                case 1:
                    System.out.println("=== CONSULTAR DISPONIBILIDADE ===");
                    System.out.print("Digite a data para consulta: ");
                    int dataConsulta = scanner.nextInt(); // Recebe a data do usuário
                    System.out.print("Digite o número do quarto: ");
                    int quartoConsulta = scanner.nextInt(); // Recebe o número do quarto
                    
                    // Procura o quarto no array de quartos da pousada
                    Quarto quartoEncontrado = null;
                    for (Quarto q : pousada.getQuartos()) {
                        if (q.getNumero() == quartoConsulta) {
                            quartoEncontrado = q; // Guarda referência do quarto encontrado
                            break; // Sai do loop quando encontra o quarto
                        }
                    }
                    
                    // Verifica se o quarto existe
                    if (quartoEncontrado == null) {
                        System.out.println("ERRO: Quarto " + quartoConsulta + " não existe!");
                    } else {
                        // Chama o método para verificar disponibilidade
                        boolean disponivel = pousada.consultaDisponibilidade(dataConsulta, quartoConsulta);
                        
                        if (disponivel) {
                            // Mostra dados do quarto disponível
                            System.out.println("QUARTO DISPONÍVEL!");
                            System.out.println("Dados do quarto:");
                            System.out.println("Número: " + quartoEncontrado.getNumero());
                            System.out.println("Categoria: " + quartoEncontrado.getCategoria());
                            System.out.println("Diária: R$ " + quartoEncontrado.getDiaria());
                        } else {
                            System.out.println("QUARTO OCUPADO na data " + dataConsulta);
                        }
                    }
                    break;

                // FUNÇÃO 2: CONSULTAR RESERVA
                case 2:
                    System.out.println("=== CONSULTAR RESERVA ===");
                    System.out.print("Digite a data: ");
                    int dataFiltro = scanner.nextInt(); // Recebe data para filtrar
                    
                    scanner.nextLine(); // Consome a quebra de linha deixada pelo nextInt()
                    System.out.print("Digite o nome do cliente: ");
                    String clienteFiltro = scanner.nextLine().trim(); // Recebe nome do cliente
                    
                    System.out.print("Digite o número do quarto: ");
                    int quartoFiltro = scanner.nextInt(); // Recebe número do quarto
                    
                    // Valida se todos os campos foram preenchidos
                    if (dataFiltro <= 0 || clienteFiltro.isEmpty() || quartoFiltro <= 0) {
                        System.out.println("ERRO: Opção inválida. Todos os campos são obrigatórios.");
                        break;
                    }
                    
                    // Chama método de consulta com os filtros informados
                    Reserva[] reservasEncontradas = pousada.consultaReserva(dataFiltro, clienteFiltro, quartoFiltro);
                    
                    // Verifica se encontrou alguma reserva
                    if (reservasEncontradas.length == 0) {
                        System.out.println("Nenhuma reserva ativa encontrada com os critérios informados.");
                    } else {
                        // Mostra todas as reservas encontradas
                        System.out.println("RESERVAS ENCONTRADAS:");
                        for (Reserva r : reservasEncontradas) {
                            System.out.println("================================");
                            System.out.println("Cliente: " + r.getCliente());
                            System.out.println("Data inicial: " + r.getDiaInicio());
                            System.out.println("Data final: " + r.getDiaFim());
                            System.out.println("Quarto: " + r.getQuarto().getNumero());
                            System.out.println("Categoria: " + r.getQuarto().getCategoria());
                            System.out.println("Diária: R$ " + r.getQuarto().getDiaria());
                            System.out.println("Status: " + r.getStatus());
                        }
                    }
                    break;
                
                // FUNÇÃO 3: REALIZAR RESERVA
                case 3:
                    System.out.println("=== REALIZAR RESERVA ===");
                    System.out.print("Digite o dia de início da reserva: ");
                    int diaInicio = scanner.nextInt(); // Recebe data de início
                    System.out.print("Digite o dia de fim da reserva: ");
                    int diaFim = scanner.nextInt(); // Recebe data de fim
                    scanner.nextLine(); // Consome a quebra de linha
                    
                    System.out.print("Digite o nome do cliente: ");
                    String cliente = scanner.nextLine(); // Recebe nome do cliente
                    
                    System.out.print("Digite o número do quarto: ");
                    int numeroQuarto = scanner.nextInt(); // Recebe número do quarto
                    
                    // Valida se as datas fazem sentido
                    if (diaFim < diaInicio) {
                        System.out.println("ERRO: Data de fim deve ser posterior à data de início!");
                        break;
                    }
                    
                    // Tenta realizar a reserva
                    boolean reservaRealizada = pousada.realizaReserva(diaInicio, diaFim, cliente, numeroQuarto);
                    
                    // Mostra resultado da operação
                    if (reservaRealizada) {
                        System.out.println("SUCESSO: Reserva realizada com sucesso!");
                        System.out.println("Cliente: " + cliente);
                        System.out.println("Quarto: " + numeroQuarto);
                        System.out.println("Período: " + diaInicio + " a " + diaFim);
                    } else {
                        System.out.println("FALHA: Não foi possível realizar a reserva.");
                        System.out.println("Motivos possíveis:");
                        System.out.println("- Quarto não existe");
                        System.out.println("- Quarto não disponível no período solicitado");
                        System.out.println("- Cliente já possui reserva ativa ou em check-in");
                    }
                    break;

                // FUNÇÃO 4: CANCELAR RESERVA
                case 4:
                    System.out.println("=== CANCELAR RESERVA ===");
                    System.out.print("Digite o nome do cliente: ");
                    String clienteCancelamento = scanner.nextLine(); // Recebe nome do cliente
                    
                    // Tenta cancelar a reserva do cliente
                    boolean reservaCancelada = pousada.cancelaReserva(clienteCancelamento);
                    
                    // Mostra resultado da operação
                    if (reservaCancelada) {
                        System.out.println("SUCESSO: Reserva cancelada com sucesso!");
                    } else {
                        System.out.println("FALHA: Não foi encontrada reserva ativa para o cliente " + clienteCancelamento);
                    }
                    break;

                case 5:

                    break;

                case 6:

                    break;

                case 7:
                    System.out.println("Clientes com reservas ativas:");
                    for(Reserva r : pousada.getReservas()){
                        if(r.getStatus()=='A') //Mostra apenas reservas ativas
                            System.out.println(r.getCliente());
                    }
                    System.out.println("Digite o nome do cliente:");
                    String clienteConsumo = scanner.nextLine();
                    boolean clienteEncontrado = false;
                    for(Reserva r: pousada.getReservas()){
                        if(r.getCliente().equals(clienteConsumo)){
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
    }
}