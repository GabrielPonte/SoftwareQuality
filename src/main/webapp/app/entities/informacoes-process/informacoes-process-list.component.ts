import { Component, Vue, Inject } from 'vue-property-decorator';
import { IInformacoesProcess } from '@/shared/model/informacoes-process.model';

import { ProcessDefinition, ProcessDefinitionService } from 'akip-vue-community';

import InformacoesProcessService from './informacoes-process.service';

@Component
export default class InformacoesProcessListComponent extends Vue {
  @Inject('informacoesProcessService') private informacoesProcessService: () => InformacoesProcessService;

  private processDefinitionService: ProcessDefinitionService = new ProcessDefinitionService();

  public bpmnProcessDefinitionId: string = 'InformacoesProcess';
  public processDefinition: ProcessDefinition = new ProcessDefinition();
  public informacoesProcessList: IInformacoesProcess[] = [];

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
    this.informacoesProcessService()
      .retrieve()
      .then(
        res => {
          this.informacoesProcessList = res.data;
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
