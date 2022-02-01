import axios from 'axios';
import { TaskBuscaPostoContext } from './task-busca-posto.model';

const baseApiUrl = 'api/informacoes-process/task-busca-posto';

export default class TaskBuscaPostoService {
  public loadContext(taskId: number): Promise<TaskBuscaPostoContext> {
    return new Promise<TaskBuscaPostoContext>((resolve, reject) => {
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

  public claim(taskId: number): Promise<TaskBuscaPostoContext> {
    return new Promise<TaskBuscaPostoContext>((resolve, reject) => {
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

  public complete(taskBuscaPostoContext: TaskBuscaPostoContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, taskBuscaPostoContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
