public class StorageModel {
    public enum OpcoesTamanho {
        PP(1), P(2), M(3), G(4), GG(5);

        private final int valor;

        OpcoesTamanho(int valorOpcao) {
            valor = valorOpcao;
        }

        public int getValor() {
            return valor;
        }
    }

    public enum OpcoesCor {
        Roxo(1), Preto(2), Branco(3), Vermelho(4), Rosa(5);

        private final int valor;

        OpcoesCor(int valorOpcao) {
            valor = valorOpcao;
        }

        public int getValor() {
            return valor;
        }
    }
}
