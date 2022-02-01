import axios from 'axios';

import { IAgendamento } from '@/shared/model/agendamento.model';

const baseApiUrl = 'api/agendamentos';

export default class AgendamentoService {
  public find(id: number): Promise<IAgendamento> {
    return new Promise<IAgendamento>((resolve, reject) => {
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
