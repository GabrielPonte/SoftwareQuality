import { Component, Vue, Inject } from 'vue-property-decorator';

import { ILocalDesejado } from '@/shared/model/local-desejado.model';
import LocalDesejadoService from './local-desejado.service';

@Component
export default class LocalDesejadoDetails extends Vue {
  @Inject('localDesejadoService') private localDesejadoService: () => LocalDesejadoService;
  public localDesejado: ILocalDesejado = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.localDesejadoId) {
        vm.retrieveLocalDesejado(to.params.localDesejadoId);
      }
    });
  }

  public retrieveLocalDesejado(localDesejadoId) {
    this.localDesejadoService()
      .find(localDesejadoId)
      .then(res => {
        this.localDesejado = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
