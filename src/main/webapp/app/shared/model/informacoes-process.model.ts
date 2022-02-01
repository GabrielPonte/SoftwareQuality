import { IInformacoes } from '@/shared/model/informacoes.model';

export interface IInformacoesProcess {
  id?: number;
  processInstance?: any | null;
  informacoes?: IInformacoes | null;
}

export class InformacoesProcess implements IInformacoesProcess {
  constructor(public id?: number, public processInstance?: any | null, public informacoes?: IInformacoes | null) {}
}
