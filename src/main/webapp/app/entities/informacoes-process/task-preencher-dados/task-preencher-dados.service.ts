import axios from 'axios';
import { TaskPreencherDadosContext } from './task-preencher-dados.model';

const baseApiUrl = 'api/informacoes-process/task-preencher-dados';

export default class TaskPreencherDadosService {
  public loadContext(taskId: number): Promise<TaskPreencherDadosContext> {
    return new Promise<TaskPreencherDadosContext>((resolve, reject) => {
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

  public claim(taskId: number): Promise<TaskPreencherDadosContext> {
    return new Promise<TaskPreencherDadosContext>((resolve, reject) => {
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

  public complete(taskPreencherDadosContext: TaskPreencherDadosContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, taskPreencherDadosContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
