import axios from 'axios';
import { PreencherDadosContext } from './preencher-dados.model';

const baseApiUrl = 'api/agendamento-process/preencher-dados';

export default class PreencherDadosService {
  public loadContext(taskId: number): Promise<PreencherDadosContext> {
    return new Promise<PreencherDadosContext>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${taskId}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public claim(taskId: number): Promise<PreencherDadosContext> {
    return new Promise<PreencherDadosContext>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${taskId}/claim`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public complete(preencherDadosContext: PreencherDadosContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, preencherDadosContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
