export interface IAgendamento {
  id?: number;
  nomeCompleto?: string | null;
  CPF?: string | null;
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
}

export class Agendamento implements IAgendamento {
  constructor(
    public id?: number,
    public nomeCompleto?: string | null,
    public CPF?: string | null,
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
    public hora?: string | null
  ) {}
}
