import { Component, Vue, Inject } from 'vue-property-decorator';
import { IAgendamentoProcess } from '@/shared/model/agendamento-process.model';

import { ProcessDefinition, ProcessDefinitionService } from 'akip-vue-community';

import AgendamentoProcessService from './agendamento-process.service';

@Component
export default class AgendamentoProcessListComponent extends Vue {
  @Inject('agendamentoProcessService') private agendamentoProcessService: () => AgendamentoProcessService;

  private processDefinitionService: ProcessDefinitionService = new ProcessDefinitionService();

  public bpmnProcessDefinitionId: string = 'AgendamentoProcess';
  public processDefinition: ProcessDefinition = new ProcessDefinition();
  public agendamentoProcessList: IAgendamentoProcess[] = [];

  public isFetchingProcessDefinition = false;
  public isFetchingProcessInstances = false;

  public mounted(): void {
    this.init();
  }

  public init(): void {
    this.retrieveProcessDefinition();
    this.retrieveProcessInstances();
  }

  public retrieveProcessDefinition() {
    this.isFetchingProcessDefinition = true;
    this.processDefinitionService.find(this.bpmnProcessDefinitionId).then(
      res => {
        this.processDefinition = res;
        this.isFetchingProcessDefinition = false;
      },
      err => {
        this.isFetchingProcessDefinition = false;
      }
    );
  }

  public retrieveProcessInstances(): void {
    this.isFetchingProcessInstances = true;
    this.agendamentoProcessService()
      .retrieve()
      .then(
        res => {
          this.agendamentoProcessList = res.data;
          this.isFetchingProcessInstances = false;
        },
        err => {
          this.isFetchingProcessInstances = false;
        }
      );
  }

  get isFetching(): boolean {
    return this.isFetchingProcessDefinition && this.isFetchingProcessInstances;
  }

  public handleSyncList(): void {
    this.retrieveProcessInstances();
  }

  public previousState(): void {
    this.$router.go(-1);
  }
}
