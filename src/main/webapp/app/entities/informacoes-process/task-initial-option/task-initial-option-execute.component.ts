import { Component, Vue, Inject } from 'vue-property-decorator';

import OpcaoEscolhidaService from '@/entities/opcao-escolhida/opcao-escolhida.service';
import { IOpcaoEscolhida } from '@/shared/model/opcao-escolhida.model';

import TaskInitialOptionService from './task-initial-option.service';
import { TaskInitialOptionContext } from './task-initial-option.model';

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
export default class TaskInitialOptionExecuteComponent extends Vue {
  private taskInitialOptionService: TaskInitialOptionService = new TaskInitialOptionService();
  private taskContext: TaskInitialOptionContext = {};

  @Inject('opcaoEscolhidaService') private opcaoEscolhidaService: () => OpcaoEscolhidaService;

  public opcaoEscolhidas: IOpcaoEscolhida[] = [];
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
    this.taskInitialOptionService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.taskInitialOptionService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {
    this.opcaoEscolhidaService()
      .retrieve()
      .then(res => {
        this.opcaoEscolhidas = res.data;
      });
  }
}
