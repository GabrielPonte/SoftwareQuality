export interface IPostoSaude {
  id?: number;
  nomePosto?: string | null;
  estadoPosto?: string | null;
  cidadePosto?: string | null;
}

export class PostoSaude implements IPostoSaude {
  constructor(
    public id?: number,
    public nomePosto?: string | null,
    public estadoPosto?: string | null,
    public cidadePosto?: string | null
  ) {}
}
