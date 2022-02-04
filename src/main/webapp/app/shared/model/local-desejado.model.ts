export interface ILocalDesejado {
  id?: number;
  localCovid?: string | null;
}

export class LocalDesejado implements ILocalDesejado {
  constructor(public id?: number, public localCovid?: string | null) {}
}
