package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.service.ContractService;
import model.service.PaypalService;

public class Program {
	public static void main(String[] args) {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Locale.setDefault(Locale.US);
		Scanner scan = new Scanner(System.in);

		System.out.println("Entre os dados do contrato:");

		System.out.print("Numero: ");
		int number = scan.nextInt();

		System.out.print("Data (dd/MM/yyyy): ");
		LocalDate date = LocalDate.parse(scan.next(), fmt);

		System.out.print("Valor de contrato: ");
		Double valueContract = scan.nextDouble();

		System.out.print("Entre com o numero de parcelas: ");
		int installments = scan.nextInt();

		Contract contract = new Contract(number, date, valueContract);
		ContractService service = new ContractService(new PaypalService()); // injeção de dependência
		service.processContract(contract, installments);

		System.out.println("Parcelas:");

		for (Installment i : contract.getInstallments()) {
			System.out.println(i);
		}

		scan.close();
	}
}
