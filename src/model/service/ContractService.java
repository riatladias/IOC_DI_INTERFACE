package model.service;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {

	private OnlinePaymentService onlinePaymentService; // dependência

	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}

	public void processContract(Contract contract, Integer months) {

		for (int i = 1; i <= months; i++) {
			Double amount = contract.getTotalValue() / months;
			amount += onlinePaymentService.interest(amount, i);
			amount += onlinePaymentService.paymentFee(amount);
			contract.addInstallment(new Installment(contract.getDate().plusMonths(i), amount));
		}
	}
}
