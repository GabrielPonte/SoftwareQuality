export interface ITipoInformacao {
  id?: number;
  tipoInfo?: string | null;
}

export class TipoInformacao implements ITipoInformacao {
  constructor(public id?: number, public tipoInfo?: string | null) {}
}
