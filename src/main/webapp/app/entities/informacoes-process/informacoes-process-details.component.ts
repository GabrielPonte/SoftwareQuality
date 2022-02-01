import { Component, Vue, Inject } from 'vue-property-decorator';

import { IInformacoesProcess } from '@/shared/model/informacoes-process.model';
import InformacoesProcessService from './informacoes-process.service';

@Component
export default class InformacoesProcessDetailsComponent extends Vue {
  @Inject('informacoesProcessService') private informacoesProcessService: () => InformacoesProcessService;
  public informacoesProcess: IInformacoesProcess = {};

  public isFetching: boolean = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.processInstanceId) {
        vm.retrieveInformacoesProcess(to.params.processInstanceId);
      }
    });
  }

  public retrieveInformacoesProcess(informacoesProcessId) {
    this.isFetching = true;
    this.informacoesProcessService()
      .find(informacoesProcessId)
      .then(
        res => {
          this.informacoesProcess = res;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public previousState() {
    this.$router.go(-1);
  }
}
