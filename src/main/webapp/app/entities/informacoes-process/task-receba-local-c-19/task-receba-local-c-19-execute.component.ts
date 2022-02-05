import { Component, Vue, Inject } from 'vue-property-decorator';

import LocalDesejadoService from '@/entities/local-desejado/local-desejado.service';
import { ILocalDesejado } from '@/shared/model/local-desejado.model';

import TaskRecebaLocalC19Service from './task-receba-local-c-19.service';
import { TaskRecebaLocalC19Context } from './task-receba-local-c-19.model';

const validations: any = {
  taskContext: {
    informacoesProcess: {
      informacoes: {},
    },
  },
};

@Component({
  validations,
})
export default class TaskRecebaLocalC19ExecuteComponent extends Vue {
  private taskRecebaLocalC19Service: TaskRecebaLocalC19Service = new TaskRecebaLocalC19Service();
  private taskContext: TaskRecebaLocalC19Context = {};

  @Inject('localDesejadoService') private localDesejadoService: () => LocalDesejadoService;

  public localDesejados: ILocalDesejado[] = [];
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
      vm.initRelationships();
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.taskRecebaLocalC19Service.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.taskRecebaLocalC19Service.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {
    this.localDesejadoService()
      .retrieve()
      .then(res => {
        this.localDesejados = res.data;
      });
  }
}
