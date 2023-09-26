package com.example.testedesoftware.basico;

import com.example.testedesoftware.banco.OperacaoIlegalException;
import com.example.testedesoftware.tdd.ContaCorrente;
import com.example.testedesoftware.tdd.Deposito;
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
        assertEquals(0, conta.getSaldo());
	}

    /*
     * Para fazer o teste anterior passar vc precisa apenas que
     * o retorno do get saldo seja ZERO. se voce implementou mais do que isso delete.
     */

    @Test
	public void testSingleDepositoIncreasesBalance() throws OperacaoIlegalException {
		conta.creditar(new Deposito("2009-10-12", 10000));

		assertEquals(10000, conta.getSaldo());
	}

    /*
     * Para o teste anterior passar vc precisou criar uma variavel para armazenar o saldo (do ingles: balence)
     */

    @Test
	public void testMultipleDepositos() {
		conta.creditar(new Deposito("2009-10-12", 100));
		conta.creditar(new Deposito("2009-10-13", 200));
		conta.creditar(new Deposito("2009-10-14", 300));

		assertEquals(600, conta.getSaldo());
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

		assertEquals(10000, conta.getSaldo());
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
			"Saldo Inicial R$ 0\n" +
			"Saldo Final R$ 0\n" +
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
	"Saldo Inicial R$ 0\n" +
	"Saldo Final R$ 0\n" +
	"Nenhuma trasacao realizada\n";

		String extrato = conta.extrato();

		assertEquals(expected, extrato);
	}

    /*
     * Neste proximo teste vc deve alterar o saldo que estava hard coded.
     */

	@Test
	public void testProduceextratoWithDifferentStartigBalancen() {
		ContaCorrente conta = new ContaCorrente("Your Name", 10000);
		String expected =
			"Conta de Your Name\n" +
			"Saldo Inicial R$ 10000\n" +
			"Saldo Final R$ 10000\n" +
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
	public void testProduceextratoWithOneDeposito() {
		ContaCorrente conta = new ContaCorrente("James Grenning", 0);

		String expected =
				"Conta de James Grenning\n" +
				"Saldo Inicial R$ 0\n" +
				"Saldo Final R$ 10000\n" +
				"2015-03-13\tDeposito\tR$ 10000\n";

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
	public void testProduceextratoWithMultipleDeposito() {
		ContaCorrente conta = new ContaCorrente("James Grenning", 0);

		String expected =
			"Conta de James Grenning\n" +
			"Saldo Inicial R$ 0\n" +
			"Saldo Final R$ 60000\n" +
			"2015-03-11\tDeposito\tR$ 10000\n"+
			"2015-03-12\tDeposito\tR$ 20000\n"+
			"2015-03-13\tDeposito\tR$ 30000\n";

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

}