export interface IInformacoes {
  id?: number;
  opcao?: string | null;
  nomeCompleto?: string | null;
  CPF?: string | null;
  email?: string | null;
  idade?: number | null;
  sintomas?: string | null;
  qtdVacinas?: number | null;
  cidade?: string | null;
  estado?: string | null;
  endereco?: string | null;
  complemento?: string | null;
  CEP?: string | null;
  nomePosto?: string | null;
  data?: Date | null;
  hora?: string | null;
  tipoDeInformacao?: string | null;
  local?: string | null;
}

export class Informacoes implements IInformacoes {
  constructor(
    public id?: number,
    public opcao?: string | null,
    public nomeCompleto?: string | null,
    public CPF?: string | null,
    public email?: string | null,
    public idade?: number | null,
    public sintomas?: string | null,
    public qtdVacinas?: number | null,
    public cidade?: string | null,
    public estado?: string | null,
    public endereco?: string | null,
    public complemento?: string | null,
    public CEP?: string | null,
    public nomePosto?: string | null,
    public data?: Date | null,
    public hora?: string | null,
    public tipoDeInformacao?: string | null,
    public local?: string | null
  ) {}
}
