export interface IOpcaoEscolhida {
  id?: number;
  opcaoInicial?: string | null;
}

export class OpcaoEscolhida implements IOpcaoEscolhida {
  constructor(public id?: number, public opcaoInicial?: string | null) {}
}
