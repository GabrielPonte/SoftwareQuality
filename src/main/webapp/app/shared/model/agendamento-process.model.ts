import { IAgendamento } from '@/shared/model/agendamento.model';

export interface IAgendamentoProcess {
  id?: number;
  processInstance?: any | null;
  agendamento?: IAgendamento | null;
}

export class AgendamentoProcess implements IAgendamentoProcess {
  constructor(public id?: number, public processInstance?: any | null, public agendamento?: IAgendamento | null) {}
}
