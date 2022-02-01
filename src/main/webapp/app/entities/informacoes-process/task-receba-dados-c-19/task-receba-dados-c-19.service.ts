import axios from 'axios';
import { TaskRecebaDadosC19Context } from './task-receba-dados-c-19.model';

const baseApiUrl = 'api/informacoes-process/task-receba-dados-c-19';

export default class TaskRecebaDadosC19Service {
  public loadContext(taskId: number): Promise<TaskRecebaDadosC19Context> {
    return new Promise<TaskRecebaDadosC19Context>((resolve, reject) => {
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

  public claim(taskId: number): Promise<TaskRecebaDadosC19Context> {
    return new Promise<TaskRecebaDadosC19Context>((resolve, reject) => {
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

  public complete(taskRecebaDadosC19Context: TaskRecebaDadosC19Context): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, taskRecebaDadosC19Context)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
