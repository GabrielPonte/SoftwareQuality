import axios from 'axios';
import { TaskRecebaLocalC19Context } from './task-receba-local-c-19.model';

const baseApiUrl = 'api/informacoes-process/task-receba-local-c-19';

export default class TaskRecebaLocalC19Service {
  public loadContext(taskId: number): Promise<TaskRecebaLocalC19Context> {
    return new Promise<TaskRecebaLocalC19Context>((resolve, reject) => {
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

  public claim(taskId: number): Promise<TaskRecebaLocalC19Context> {
    return new Promise<TaskRecebaLocalC19Context>((resolve, reject) => {
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

  public complete(taskRecebaLocalC19Context: TaskRecebaLocalC19Context): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, taskRecebaLocalC19Context)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
