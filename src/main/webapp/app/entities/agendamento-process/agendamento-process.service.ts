import axios from 'axios';

import { IAgendamentoProcess } from '@/shared/model/agendamento-process.model';

const baseApiUrl = 'api/agendamento-processes';

export default class AgendamentoProcessService {
  public find(id: number): Promise<IAgendamentoProcess> {
    return new Promise<IAgendamentoProcess>((resolve, reject) => {
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

  public create(entity: IAgendamentoProcess): Promise<IAgendamentoProcess> {
    return new Promise<IAgendamentoProcess>((resolve, reject) => {
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
