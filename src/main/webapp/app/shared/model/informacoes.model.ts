import { ILocalDesejado } from '@/shared/model/local-desejado.model';
import { IOpcaoEscolhida } from '@/shared/model/opcao-escolhida.model';
import { IPostoSaude } from '@/shared/model/posto-saude.model';
import { IRecebaEmail } from '@/shared/model/receba-email.model';
import { ITipoInformacao } from '@/shared/model/tipo-informacao.model';

export interface IInformacoes {
  id?: number;
  opcao?: string | null;
  nomeCompleto?: string | null;
  cpf?: string | null;
  email?: string | null;
  idade?: number | null;
  sintomas?: string | null;
  qtdVacinas?: number | null;
  cidade?: string | null;
  estado?: string | null;
  endereco?: string | null;
  complemento?: string | null;
  cep?: string | null;
  nomeDoPosto?: string | null;
  estadoDoPosto?: string | null;
  cidadeDoPosto?: string | null;
  data?: Date | null;
  hora?: string | null;
  tipoDeInformacao?: string | null;
  local?: string | null;
  receberEmail?: string | null;
  localDesejado?: ILocalDesejado | null;
  opcaoEscolhida?: IOpcaoEscolhida | null;
  postoSaude?: IPostoSaude | null;
  recebaEmail?: IRecebaEmail | null;
  tipoInformacao?: ITipoInformacao | null;
}

export class Informacoes implements IInformacoes {
  constructor(
    public id?: number,
    public opcao?: string | null,
    public nomeCompleto?: string | null,
    public cpf?: string | null,
    public email?: string | null,
    public idade?: number | null,
    public sintomas?: string | null,
    public qtdVacinas?: number | null,
    public cidade?: string | null,
    public estado?: string | null,
    public endereco?: string | null,
    public complemento?: string | null,
    public cep?: string | null,
    public nomeDoPosto?: string | null,
    public estadoDoPosto?: string | null,
    public cidadeDoPosto?: string | null,
    public data?: Date | null,
    public hora?: string | null,
    public tipoDeInformacao?: string | null,
    public local?: string | null,
    public receberEmail?: string | null,
    public localDesejado?: ILocalDesejado | null,
    public opcaoEscolhida?: IOpcaoEscolhida | null,
    public postoSaude?: IPostoSaude | null,
    public recebaEmail?: IRecebaEmail | null,
    public tipoInformacao?: ITipoInformacao | null
  ) {}
}
