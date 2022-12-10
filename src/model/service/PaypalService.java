package model.service;

public class PaypalService implements OnlinePaymentService{

	@Override
	public double paymentFee(Double amount) {
		// taxa de 2% por pagamento
		return amount * 0.02;
	}

	@Override
	public double interest(Double amount, Integer months) {
		// 1% juros simples mensal
		return amount * 0.01 * months;
	}

}
