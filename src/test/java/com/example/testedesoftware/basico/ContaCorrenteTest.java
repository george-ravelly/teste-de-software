package com.example.testedesoftware.basico;

import com.example.testedesoftware.banco.OperacaoIlegalException;
import com.example.testedesoftware.banco.tdd.ContaCorrente;
import com.example.testedesoftware.banco.tdd.Deposito;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;


/*
 * Voce irá escrever a classe ContaCorrente guiada pela bateria de testes definida abaixo
 *
 *
 *  Descomente os testes 1 por 1, e escreva a quantidade mínima de código para o teste passar.
 *  Em alguns casos isto significa um resultado "hard coded".
 *  Não se preocupe, pois o teste seguinte irá lhe forçar a evoluir a implementação
 *  para checar numa implementacao real da conta corrente.
 *
 * Primeiramente, faça o teste falhar, isto fará perceber que
 * o teste pode detectar falhas. Aí altere o código da classe sendo implementada
 * para o teste passar.
 */

@SpringBootTest
public class ContaCorrenteTest  {

    private ContaCorrente conta;

    /*
     * executando antes de cada metodo de teste
     */

    @Before
    public void setUp() {
        conta = new ContaCorrente();
    }

    /*
     * executado apos cada metodo de teste
     */
    public void tearDown() {

    }

    @Test
	public void testANewCheckBookHasAZeroBallanceByDefault() {
//        conta = new ContaCorrente("123", 20.1);
        assertEquals(0, conta.getSaldo(), 2);
	}

    /*
     * Para fazer o teste anterior passar vc precisa apenas que
     * o retorno do get saldo seja ZERO. se voce implementou mais do que isso delete.
     */

    @Test
	public void testSingleDepositoIncreasesBalance() throws OperacaoIlegalException {
		conta.creditar(new Deposito("2009-10-12", 10000));

		assertEquals(10000, conta.getSaldo(), 2);
	}

    /*
     * Para o teste anterior passar vc precisou criar uma variavel para armazenar o saldo (do ingles: balence)
     */

    @Test
	public void testMultipleDepositos() throws OperacaoIlegalException {
		conta.creditar(new Deposito("2009-10-12", 100));
		conta.creditar(new Deposito("2009-10-13", 200));
		conta.creditar(new Deposito("2009-10-14", 300));

		assertEquals(600, conta.getSaldo(), 2);
	}

    /*
     * Para o teste anterior passar vc deve ser capaz de somar cada um dos depositos ao saldo.
     */

    /*
     * Para o proximo teste passar vc precisará de um novo construtor.
     */

    @Test
	public void testCreatecontaWithInitialBalance() {
		ContaCorrente conta = new ContaCorrente(10000);

		assertEquals(10000, conta.getSaldo(), 2);
	}

    /*
     * Para o proximo teste passar você precisará guardar uma informacao a mais:
     * o nome do dono da conta.
     *
     * Inicialmente vc nao deve se preocupar com isso, vc pode retornar um string
     * hard coded para os testes passarem. Os testes seguintes
     * farao com que vc precise deixar de usar este valor hard coded.
     *
     */

    @Test
	public void testExtrato() {
		String extrato_esperado =
			"Conta de null\n" +
			"Saldo Inicial R$ 0,00\n" +
			"Saldo Final R$ 0,00\n" +
			"Nenhuma trasacao realizada\n";

		String extrato_real = conta.extrato();

		assertEquals(extrato_esperado, extrato_real);
	}

    /*
     * Para este teste vc precisará de um nome para o dono da conta.
     */

	@Test
	public void testProduceextratoWithDifferentHolder() {
		ContaCorrente conta = new ContaCorrente("James Grenning");
		String expected =
			"Conta de James Grenning\n" +
			"Saldo Inicial R$ 0,00\n" +
			"Saldo Final R$ 0,00\n" +
			"Nenhuma trasacao realizada\n";

		String extrato = conta.extrato();

		assertEquals(expected, extrato);
	}

    /*
     * Neste proximo teste vc deve alterar o saldo que estava hard coded.
     */

	@Test
	public void testProduceextratoWithDifferentStartigBalancen() {
		ContaCorrente conta = new ContaCorrente("George", 10000);
		String expected =
			"Conta de George\n" +
			"Saldo Inicial R$ 10000,00\n" +
			"Saldo Final R$ 10000,00\n" +
			"Nenhuma trasacao realizada\n";
		String extrato = conta.extrato();

		assertEquals(expected, extrato);
	}

    /*
     * Até agora só tinhamos que armazenar o historico do saldo,
     * agora precisaremos manter o historico de varias transacaoes:
     * deposito e saque. Neste teste vc vai realizar um deposito e Precisa armazenar esta informação no extrato.
     */

	@Test
	public void testProduceextratoWithOneDeposito() throws OperacaoIlegalException {
		ContaCorrente conta = new ContaCorrente("James Grenning", 0);

		String expected =
				"Conta de James Grenning\n" +
				"Saldo Inicial R$ 0,00\n" +
				"Saldo Final R$ 10000,00\n" +
				"2015-03-13\tDeposito\tR$ 10000,00\n";

		conta.creditar(new Deposito("2015-03-13", 10000));

		String extrato = conta.extrato();

		assertEquals(expected, extrato);
	}

    /*
     * Note que esta abordagem de desenvolvimento faz com que vc
     * foque em uma única mudança de cada vez. E cada mudança será testada.
     *
     * Agora vamos incluir um conjunto de depositos.
     * Isto irá forçar vc a criar uma coleção, e percorrer esta
     * colecao para gerar o extrato.
     *
     * Não esqueça que o código de testes deve rodar antes de
     * construir a implementação que faça ele passar:
     * LEMA DO TDD: RED BAR + GREEN BAR + REFATORAMENTO (QUANDO NECESSÁRIO)
     */

	@Test
	public void testProduceextratoWithMultipleDeposito() throws OperacaoIlegalException {
		ContaCorrente conta = new ContaCorrente("James Grenning", 0);

		String expected =
			"Conta de James Grenning\n" +
			"Saldo Inicial R$ 0,00\n" +
			"Saldo Final R$ 60000,00\n" +
			"2015-03-11\tDeposito\tR$ 10000,00\n"+
			"2015-03-12\tDeposito\tR$ 20000,00\n"+
			"2015-03-13\tDeposito\tR$ 30000,00\n";

			conta.creditar(new Deposito("2015-03-11", 10000));
			conta.creditar(new Deposito("2015-03-12", 20000));
			conta.creditar(new Deposito("2015-03-13", 30000));

		String extrato = conta.extrato();

		assertEquals(expected, extrato);
	}

    /*
     * Agora se quiser continue a implementar outros métodos pra esta classe
     * seguindo o fluxo TDD.
     */

	@Test
	public void testTransferirEntreContas() throws OperacaoIlegalException {
		ContaCorrente contaPrincipal = new ContaCorrente("João", 0);
		ContaCorrente contaDestino = new ContaCorrente("Carlos", 0);

		String expected =
				"Conta de João\n" +
						"Saldo Inicial R$ 0,00\n" +
						"Saldo Final R$ 2000,00\n" +
						"2015-03-14\tDeposito\tR$ 3000,00\n"+
						"2015-03-14\tRetirada\tR$ 1000,00\n";

		contaPrincipal.creditar(new Deposito("2015-03-14", 3000));

		contaPrincipal.transferir(contaDestino, 1000);

		assertEquals(2000, contaPrincipal.getSaldo(), 2);
		assertEquals(1000, contaDestino.getSaldo(), 2);
		assertEquals(expected, contaPrincipal.extrato());
	}

	@Test
	public void testTransferirEntreContasException() {
		ContaCorrente contaPrincipal = new ContaCorrente("João", 0);
		ContaCorrente contaDestino = new ContaCorrente("Carlos", 0);

		try {
			contaPrincipal.creditar(new Deposito("2015-03-14", 3000));

			contaPrincipal.transferir(contaDestino, 3500);

			assertEquals(2000, contaPrincipal.getSaldo(), 2);
			assertEquals(1000, contaDestino.getSaldo(), 2);

			fail("Não deve ser possivel sacar além do saldo!");
		} catch (OperacaoIlegalException e) {
			e.printStackTrace();
		}
	}
}