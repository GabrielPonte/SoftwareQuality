import axios from 'axios';

import { IInformacoesProcess } from '@/shared/model/informacoes-process.model';

const baseApiUrl = 'api/informacoes-processes';

export default class InformacoesProcessService {
  public find(id: number): Promise<IInformacoesProcess> {
    return new Promise<IInformacoesProcess>((resolve, reject) => {
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

  public create(entity: IInformacoesProcess): Promise<IInformacoesProcess> {
    return new Promise<IInformacoesProcess>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
