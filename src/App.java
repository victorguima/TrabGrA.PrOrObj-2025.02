import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Pousada pousada = new Pousada();
        System.out.println(pousada.getNome());
        System.out.println(pousada.getContato());
        for(Quarto q: pousada.getQuartos()){
            System.out.println(q.toString());
        }

        System.out.println();//Linha em branco para separar o lixo visual que apareceu antes
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
        Scanner scanner = new Scanner(System.in);
        int codigo=scanner.nextInt();
        while(codigo != 0){
            switch (codigo) {
                case 1:

                    break;
            
                default:
                    break;
            }
            codigo = scanner.nextInt();
        }
        
        scanner.close();

    }
}
