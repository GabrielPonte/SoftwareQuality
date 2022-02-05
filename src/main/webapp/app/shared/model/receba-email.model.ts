export interface IRecebaEmail {
  id?: number;
  opcaoEmail?: string | null;
}

export class RecebaEmail implements IRecebaEmail {
  constructor(public id?: number, public opcaoEmail?: string | null) {}
}
