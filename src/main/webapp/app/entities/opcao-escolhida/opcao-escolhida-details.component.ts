import { Component, Vue, Inject } from 'vue-property-decorator';

import { IOpcaoEscolhida } from '@/shared/model/opcao-escolhida.model';
import OpcaoEscolhidaService from './opcao-escolhida.service';

@Component
export default class OpcaoEscolhidaDetails extends Vue {
  @Inject('opcaoEscolhidaService') private opcaoEscolhidaService: () => OpcaoEscolhidaService;
  public opcaoEscolhida: IOpcaoEscolhida = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.opcaoEscolhidaId) {
        vm.retrieveOpcaoEscolhida(to.params.opcaoEscolhidaId);
      }
    });
  }

  public retrieveOpcaoEscolhida(opcaoEscolhidaId) {
    this.opcaoEscolhidaService()
      .find(opcaoEscolhidaId)
      .then(res => {
        this.opcaoEscolhida = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
