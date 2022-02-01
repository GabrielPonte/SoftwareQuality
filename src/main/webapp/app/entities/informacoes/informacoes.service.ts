import axios from 'axios';

import { IInformacoes } from '@/shared/model/informacoes.model';

const baseApiUrl = 'api/informacoes';

export default class InformacoesService {
  public find(id: number): Promise<IInformacoes> {
    return new Promise<IInformacoes>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public retrieve(): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(baseApiUrl)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
