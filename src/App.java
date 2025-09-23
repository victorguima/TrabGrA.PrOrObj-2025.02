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
            scanner.nextLine(); //consome a quebra de linha deixada pelo nextInt()
            String cliente = "NULO"; //Inicializa a variável cliente
            switch (codigo) {
                case 1:
                    System.out.println("=== CONSULTAR DISPONIBILIDADE ===");
                    System.out.println("Reservas registradas:");
                    for(Reserva r: pousada.getReservas()){
                        System.out.println(r.toString());
                    }
                    System.out.print("Digite a data para consulta: ");
                    int dataConsulta = scanner.nextInt();
                    if(dataConsulta <= 0 || dataConsulta > 31){ //Validação simples de data
                        System.out.println("ERRO: Data inválida!");
                        System.out.println("Pressione qualquer tecla para continuar...");
                        scanner.nextLine(); //consome \n
                        scanner.nextLine(); //espera o usuário digitar algo
                        break;
                    }
                    System.out.print("Digite o número do quarto: ");
                    int quartoConsulta = scanner.nextInt();
                    
                    Quarto quartoEncontrado = null;
                    for (Quarto q : pousada.getQuartos()) {
                        if (q.getNumero() == quartoConsulta) {
                            quartoEncontrado = q;
                            break;
                        }
                    }
                    
                    if (quartoEncontrado == null) {
                        System.out.println("ERRO: Quarto " + quartoConsulta + " não existe!");
                    } else {
                        boolean disponivel = pousada.consultaDisponibilidade(dataConsulta, quartoConsulta);
                        
                        if (disponivel) {
                            System.out.println("QUARTO DISPONÍVEL!");
                            System.out.println("Dados do quarto:");
                            System.out.println("Número: " + quartoEncontrado.getNumero());
                            System.out.println("Categoria: " + quartoEncontrado.getCategoria());
                            System.out.println("Diária: R$ " + quartoEncontrado.getDiaria());
                        } else {
                            System.out.println("QUARTO OCUPADO na data " + dataConsulta);
                        }
                    }
                    System.out.println("Pressione qualquer tecla para continuar...");
                    scanner.nextLine(); //consome \n
                    scanner.nextLine(); //espera o usuário digitar algo
                    break;

                case 2:
                    System.out.println("=== CONSULTAR RESERVA ===");
                    System.out.println("Reservas ativas encontradas:");
                    for(Reserva r: pousada.getReservas()){
                        if(r.getStatus()=='A')
                        System.out.println(r.toString());
                    }
                    System.out.print("Digite a data: ");
                    int dataFiltro = scanner.nextInt();
                    
                    scanner.nextLine(); //consome a quebra de linha deixada pelo nextInt()
                    System.out.print("Digite o nome do cliente: ");
                    String clienteFiltro = scanner.nextLine().trim();
                    
                    System.out.print("Digite o número do quarto: ");
                    int quartoFiltro = scanner.nextInt();
                    
                    if (dataFiltro <= 0 || clienteFiltro.isEmpty() || quartoFiltro <= 0) {
                        System.out.println("ERRO: Opção inválida. Todos os campos são obrigatórios.");
                        break;
                    }
                    
                    Reserva[] reservasEncontradas = pousada.consultaReserva(dataFiltro, clienteFiltro, quartoFiltro);
                    
                    if (reservasEncontradas.length == 0) {
                        System.out.println("Nenhuma reserva ativa encontrada com os critérios informados.");
                    } else {
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
                    System.out.println("Pressione qualquer tecla para continuar...");
                    scanner.nextLine(); //consome \n
                    scanner.nextLine(); //espera o usuário digitar algo
                    break;
                
                case 3:
                    System.out.println("=== REALIZAR RESERVA ===");
                    System.out.println("Reservas registradas:");
                    for(Reserva r: pousada.getReservas()){
                        System.out.println(r.toString());
                    }
                    System.out.print("Digite o dia de início da reserva: ");
                    int diaInicio = scanner.nextInt();
                    System.out.print("Digite o dia de fim da reserva: ");
                    int diaFim = scanner.nextInt();
                    scanner.nextLine(); //consome a quebra de linha

                    if (diaFim < diaInicio) {
                        System.out.println("ERRO: Data de fim deve ser posterior à data de início!");
                        System.out.println("Pressione qualquer tecla para continuar...");
                        scanner.nextLine(); //espera o usuário digitar algo
                        break;
                    }
                    
                    System.out.print("Digite o nome do cliente: ");
                    cliente = scanner.nextLine();
                    
                    System.out.print("Digite o número do quarto: ");
                    int numeroQuarto = scanner.nextInt();
                    
                    
                    
                    boolean reservaRealizada = pousada.realizaReserva(diaInicio, diaFim, cliente, numeroQuarto);
                    
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
                    System.out.println("Pressione qualquer tecla para continuar...");
                    scanner.nextLine(); //consome \n
                    scanner.nextLine(); //espera o usuário digitar algo
                    break;

                case 4:
                    System.out.println("=== CANCELAR RESERVA ===");
                    System.out.println("Reservas ativas:");
                    for(Reserva r: pousada.getReservas()){
                        if(r.getStatus()=='A') //Mostra apenas reservas ativas
                            System.out.println(r.getCliente()+" - Quarto "+r.getQuarto().getNumero() + ", de dias "+r.getDiaInicio()+" a "+r.getDiaFim());
                    }
                    System.out.print("Digite o nome do cliente: ");
                    String clienteCancelamento = scanner.nextLine();
                    
                    boolean reservaCancelada = pousada.cancelaReserva(clienteCancelamento);
                    
                    if (reservaCancelada) {
                        System.out.println("SUCESSO: Reserva cancelada com sucesso!");
                    } else {
                        System.out.println("FALHA: Não foi encontrada reserva ativa para o cliente " + clienteCancelamento);
                    }
                    System.out.println("Pressione qualquer tecla para continuar...");
                    scanner.nextLine(); //espera o usuário digitar algo
                    break;

                case 5:
                    System.out.println("=== REALIZAR CHECK-IN ===");
                    System.out.print("Digite o nome do cliente: ");
                    System.out.println("Clientes com reserva ativa:");
                    for(Reserva r : pousada.getReservas()){
                        if(r.getStatus()=='A') //Mostra apenas reservas ativas
                            System.out.println(r.getCliente()+" - Quarto "+r.getQuarto().getNumero() + ", de dias "+r.getDiaInicio()+" a "+r.getDiaFim());
                    }
                    String clienteCheckIn = scanner.nextLine();
                    pousada.realizaCheckIn(clienteCheckIn);
                    System.out.println("Pressione qualquer tecla para continuar...");
                    scanner.nextLine(); //espera o usuário digitar algo
                    break;

                case 6:
                    System.out.println("=== REALIZAR CHECK-OUT ===");
                    System.out.println("Clientes que já fizeram check-in:");
                    for(Reserva r : pousada.getReservas()){
                        if(r.getStatus()=='I') //Mostra apenas reservas com check-in realizado
                            System.out.println(r.getCliente()+" - Quarto "+r.getQuarto().getNumero() + ", de dias "+r.getDiaInicio()+" a "+r.getDiaFim());
                    }
                    System.out.print("Digite o nome do cliente: ");
                    String clienteCheckOut = scanner.nextLine();
                    pousada.realizaCheckOut(clienteCheckOut);
                    System.out.println("Pressione qualquer tecla para continuar...");
                    scanner.nextLine(); //espera o usuário digitar algo
                    break;

                case 7:
                    System.out.println("Clientes que já fizeram check-in:");
                    for(Reserva r : pousada.getReservas()){
                        if(r.getStatus()=='I') //Mostra apenas reservas com check-in realizado TESTE AAAA
                            System.out.println(r.getCliente()+" - Quarto "+r.getQuarto().getNumero() + ", de dias "+r.getDiaInicio()+" a "+r.getDiaFim());
                    }
                    System.out.println("Digite o nome do cliente:");
                    cliente = scanner.nextLine();
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
                    System.out.println("Pressione qualquer tecla para continuar...");
                    scanner.nextLine(); //consome \n
                    scanner.nextLine(); //espera o usuário digitar algo
                    break;

                case 8:
                    pousada.salvaDados(); //Salva os dados atuais nos arquivos
                    System.out.println("Pressione qualquer tecla para continuar...");
                    scanner.nextLine(); //consome \n
                    scanner.nextLine(); //espera o usuário digitar algo
                    break;
            
                default:
                    break;
            }
        } while(codigo != 0);
        scanner.close();
        for(Quarto q: pousada.getQuartos()){ //Mostra os atributos dos quartos e seus consumos
            System.out.println(q.toString());
            if(q.getConsumo().length>0){//Se o quarto tiver consumos registrados
                System.out.println("Consumo do quarto "+q.getNumero()+":");
                q.listaConsumo(pousada.getProdutos());
                q.valorTotalConsumo(pousada.getProdutos());
            }
        }
    }
}
